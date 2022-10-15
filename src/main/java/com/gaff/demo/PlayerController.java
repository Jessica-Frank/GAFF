package com.gaff.demo;

/*
 * This controller is for player-specific sections of the site.
 * Last updated 10/15/2022
 * Author(s): Alec Droegemeier
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlayerController {    
    @GetMapping("/game_list")
    public String addGameSearch(Model model) {
        return "GameList";
    }
    
    @PostMapping("/game_list/game")
    public String addGameConfirm(Model model) {
        return "GameTemplate";
    }
    
    @PostMapping("/player_profile")
    public String addGameSubmit(Model model) {
        return "Profile";
    }
}
