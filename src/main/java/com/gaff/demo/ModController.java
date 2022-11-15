package com.gaff.demo;

/*
 * This controller is for moderator-specific sections of the site.
 * Last updated 11/14/2022
 * Author(s): Jessica Frank
 */
import com.gaff.demo.models.ActionRepository;
import com.gaff.demo.models.Game;
import com.gaff.demo.models.GameRepository;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModController {

    @Autowired
    GameRepository gameRep;
    
    @Autowired
    ActionRepository actionRep;

    @GetMapping("/add_game/search")
    public String addGameSearch(Model model) {
        return "AddGameSearch";
    }

    @PostMapping("/add_game/details")
    public String addGameConfirm(Model model, @RequestParam String gameName,
            @RequestParam String apiKey) {

        //fill out the default information if the name or key wasn't entered
        if (gameName.equals("") || apiKey.equals("")) {
            model.addAttribute("subheadingText", "Game name or Key was not entered");
            model.addAttribute("actualName", gameName);
            model.addAttribute("genre", "action");
            model.addAttribute("isPC", false);
            model.addAttribute("isConsole", false);
            model.addAttribute("isMobile", false);

            return "AddGameDetails";
        }

        //if the method reaches this point, try to search with the given name and key
        try {
            //encode the data sent to the API and format the URL
            String encodedSearch = URLEncoder.encode(gameName, Charset.defaultCharset());
            String encodedKey = URLEncoder.encode(apiKey, Charset.defaultCharset());
            String url = "https://api.rawg.io/api/games?search=" + encodedSearch + "&key=" + encodedKey + "&pageSize=1";

            //send the request to the API
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //get the JSON-formatted response
            JSONObject responseObj = new JSONObject(response.body());
            JSONArray results = responseObj.getJSONArray("results");
            JSONObject firstResult = results.getJSONObject(0);

            model.addAttribute("subheadingText", "\"" + gameName + "\" game data from RAWG API");
            model.addAttribute("actualName", firstResult.get("name"));

            //loop through the list of genres to find if there's one that matches a GAFF genre
            JSONArray genreArray = firstResult.getJSONArray("genres");
            String genre = "action";
            String[] genreList = {"Action","Adventure","Simulation","Puzzle","Racing","Sports"};
            genreLoop:
            for (Object genreObj : genreArray) {
                String genreName = ((JSONObject) genreObj).getString("name");
                if (Arrays.stream(genreList).anyMatch(genreName::equals)){
                    genre = genreName.toLowerCase();
                    break;
                }
            }
            model.addAttribute("genre", genre);

            //create the variables that track the platforms that were found
            boolean isPC = false;
            boolean isConsole = false;
            boolean isMobile = false;

            //loop through the list of platforms to check for pc, console, or mobile platforms
            JSONArray platforms = firstResult.getJSONArray("platforms");
            for (Object platformObj : platforms) {
                JSONObject platformWrapper = ((JSONObject) platformObj).getJSONObject("platform");
                String platformName = platformWrapper.getString("name").toLowerCase();
                if (platformName.equals("pc")) {
                    isPC = true;
                } else if (platformName.equals("ios")) {
                    isMobile = true;
                } else if (platformName.equals("android")) {
                    isMobile = true;
                } else if (platformName.contains("playstation")) {
                    isConsole = true;
                } else if (platformName.contains("xbox")) {
                    isConsole = true;
                }

                if (isPC && isConsole && isMobile) {
                    break;
                }
            }
            model.addAttribute("isPC", isPC);
            model.addAttribute("isConsole", isConsole);
            model.addAttribute("isMobile", isMobile);

            return "AddGameDetails";
        } catch (Exception ex) {
            //if there was an error accessing the API, fill out the form with the default information
            model.addAttribute("subheadingText", "An error occured while searching for game details");
            model.addAttribute("actualName", gameName);
            model.addAttribute("genre", "action");
            model.addAttribute("isPC", false);
            model.addAttribute("isConsole", false);
            model.addAttribute("isMobile", false);

            ex.printStackTrace();
            return "AddGameDetails";
        }
    }

    @PostMapping("/add_game/submit")
    public String addGameSubmit(Model model, @RequestParam String name,
            @RequestParam String details, @RequestParam String genreOptions,
            @RequestParam String[] platformOptions) {

        String platformString = "";
        boolean isPC = false;
        boolean isConsole = false;
        boolean isMobile = false;

        for (String option : platformOptions) {
            if (!(platformOptions.length > 1 && option.equals("None"))) {
                platformString += " " + option;
            }

            switch (option) {
                case "PC" ->
                    isPC = true;
                case "Console" ->
                    isConsole = true;
                case "Mobile" ->
                    isMobile = true;
            }
        }

        //Add the game to the database
        gameRep.addNewGame(name, genreOptions, details, isPC, isConsole, isMobile);
        actionRep.addAction(name + " was added as a(n) " + genreOptions + " game", "Moderator");

        //Send game information to the confirmation page
        model.addAttribute("name", name);
        model.addAttribute("details", details);
        model.addAttribute("genre", genreOptions);
        model.addAttribute("platforms", platformString);

        model.addAttribute("action", "Added");
        return "GameChangeConfirmation";
    }

    @GetMapping("/edit_game/id={id}")
    public String editGame(Model model, @PathVariable("id") long id) {
        Game game = gameRep.getGameById(id);

        model.addAttribute("id", id);
        model.addAttribute("currentName", game.getName());
        model.addAttribute("newName", game.getName());
        model.addAttribute("currentDetails", game.getDescription());
        model.addAttribute("genre", game.getGenre());
        model.addAttribute("isPC", game.getIsComputer());
        model.addAttribute("isConsole", game.getIsConsole());
        model.addAttribute("isMobile", game.getIsMobile());

        return "EditGameDetails";
    }

    @PostMapping("/edit_game/submit")
    public String editGameSubmit(Model model, @RequestParam String name,
            @RequestParam String details, @RequestParam String genreOptions,
            @RequestParam String[] platformOptions, @RequestParam long id) {

        String platformString = "";
        boolean isPC = false;
        boolean isConsole = false;
        boolean isMobile = false;

        for (String option : platformOptions) {
            if (!(platformOptions.length > 1 && option.equals("None"))) {
                platformString += " " + option;
            }

            switch (option) {
                case "PC" ->
                    isPC = true;
                case "Console" ->
                    isConsole = true;
                case "Mobile" ->
                    isMobile = true;
            }
        }

        gameRep.editGame(id, name, genreOptions, details, isPC, isConsole, isMobile);
        actionRep.addAction(name + " was edited", "Moderator");

        model.addAttribute("name", name);
        model.addAttribute("details", details);
        model.addAttribute("genre", genreOptions);
        model.addAttribute("platforms", platformString);

        model.addAttribute("action", "Edited");
        return "GameChangeConfirmation";
    }
}
