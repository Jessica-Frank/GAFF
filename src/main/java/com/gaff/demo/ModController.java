package com.gaff.demo;

/*
 * This controller is for moderator-specific sections of the site.
 * Last updated 10/28/2022
 * Author(s): Jessica Frank
 */
import com.gaff.demo.models.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModController {

    @Autowired
    GameRepository gameRep;

    @GetMapping("/add_game/search")
    public String addGameSearch(Model model) {
        return "AddGameSearch";
    }

    @PostMapping("/add_game/details")
    public String addGameConfirm(Model model, @RequestParam String gameName,
            @RequestParam String apiKey) {
        model.addAttribute("nameEntered", gameName);

        //When the API is implemented, the data below will be from the API
        model.addAttribute("actualName", gameName);
        String genre = "puzzle";
        model.addAttribute("genre", genre);
        model.addAttribute("isPC", true);
        model.addAttribute("isConsole", false);
        model.addAttribute("isMobile", true);

        return "AddGameDetails";
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
            if (platformOptions.length > 1 && !option.equals("None")) {
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

        //Send game information to the confirmation page
        model.addAttribute("name", name);
        model.addAttribute("details", details);
        model.addAttribute("genre", genreOptions);
        model.addAttribute("platforms", platformString);

        model.addAttribute("action", "Added");
        return "GameChangeConfirmation";
    }

    @GetMapping("/edit_game")
    public String editGame(Model model) {
        model.addAttribute("currentName", "GAME NAME");
        model.addAttribute("newName", "GAME NAME");
        model.addAttribute("currentDetails", "GAME NAME is a cool game.");

        String genre = "action";
        model.addAttribute("genre", genre);
        model.addAttribute("isPC", false);
        model.addAttribute("isConsole", false);
        model.addAttribute("isMobile", true);

        return "EditGameDetails";
    }

    @PostMapping("/edit_game/submit")
    public String editGameSubmit(Model model, @RequestParam String name,
            @RequestParam String details, @RequestParam String genreOptions,
            @RequestParam String[] platformOptions) {

        String platformString = platformOptions[0];
        for (int i = 1; i < platformOptions.length; i++) {
            platformString += ", " + platformOptions[i];
        }

        model.addAttribute("name", name);
        model.addAttribute("details", details);
        model.addAttribute("genre", genreOptions);
        model.addAttribute("platforms", platformString);

        model.addAttribute("action", "Edited");
        return "GameChangeConfirmation";
    }
}
