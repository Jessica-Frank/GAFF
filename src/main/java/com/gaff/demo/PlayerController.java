package com.gaff.demo;

/*
 * This controller is for player-specific sections of the site.
 * Last updated 10/28/2022
 * Author(s): Alec Droegemeier, Jessica Frank
 */

import com.gaff.demo.models.Game;
import com.gaff.demo.models.GameRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PlayerController {

    @Autowired
    GameRepository gameRep;

    @GetMapping("/game_list")
    public String getGameList(Model model) {
        List<Game> games = gameRep.getAllGames();
        model.addAttribute("gameList", games);
        return "GameList";
    }

    @GetMapping("/game/{id}")
    public String viewGame(Model model, @PathVariable("id") long id) {
        Game game = gameRep.getGameById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasModRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_MOD"));
        model.addAttribute("hasModRole", hasModRole);
        
        model.addAttribute("id", id);
        model.addAttribute("name", game.getName());
        model.addAttribute("details", game.getDescription());
        model.addAttribute("genre", game.getGenre());
        model.addAttribute("isPC", game.getIsComputer());
        model.addAttribute("isConsole", game.getIsConsole());
        model.addAttribute("isMobile", game.getIsMobile());

        return "GameTemplate";
    }

    @GetMapping("/genre/action")
    public String getActionGenre(Model model) {
        List<Game> actionGames = gameRep.getActionGames();
        model.addAttribute("gameList", actionGames);
        return "GameList";
    }

    @GetMapping("/genre/adventure")
    public String getAdventureGenre(Model model) {
        List<Game> adventureGames = gameRep.getAdventureGames();
        model.addAttribute("gameList", adventureGames);
        return "GameList";
    }

    @GetMapping("/genre/simulation")
    public String getSimulationGenre(Model model) {
        List<Game> simulationGames = gameRep.getSimulationGames();
        model.addAttribute("gameList", simulationGames);
        return "GameList";
    }

    @GetMapping("/genre/puzzle")
    public String getPuzzleGenre(Model model) {
        List<Game> puzzleGames = gameRep.getPuzzleGames();
        model.addAttribute("gameList", puzzleGames);
        return "GameList";
    }

    @GetMapping("/genre/racing")
    public String getRacingGenre(Model model) {
        List<Game> racingGames = gameRep.getRacingGames();
        model.addAttribute("gameList", racingGames);
        return "GameList";
    }

    @GetMapping("/genre/sports")
    public String getSportsGenre(Model model) {
        List<Game> sportsGames = gameRep.getSportsGames();
        model.addAttribute("gameList", sportsGames);
        return "GameList";
    }

    @GetMapping("/player_profile")
    public String playerProfile(Model model) {
        return "Profile";
    }
}
