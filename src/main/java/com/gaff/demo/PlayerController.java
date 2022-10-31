package com.gaff.demo;

/*
 * This controller is for player-specific sections of the site.
 * Last updated 10/28/2022
 * Author(s): Alec Droegemeier, Jessica Frank
 */
import com.gaff.demo.models.AppUser;
import com.gaff.demo.models.Game;
import com.gaff.demo.models.GameRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/game_details")
    public String getGameDetails(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasModRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_" + AppUser.ROLE_MODERATOR));
        model.addAttribute("hasModRole", hasModRole);
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
