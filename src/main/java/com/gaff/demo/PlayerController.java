package com.gaff.demo;

/*
 * This controller is for player-specific sections of the site.
 * Last updated 10/15/2022
 * Author(s): Alec Droegemeier, Jessica Frank
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlayerController {    
    @GetMapping("/game_list")
    public String addGameSearch(Model model) {
        return "GameList";
    }
    
    @GetMapping("/game_details")
    public String addGameConfirm(Model model) {
        return "GameTemplate";
    }
    
    @GetMapping("/player_profile")
    public String addGameSubmit(Model model) {
        return "Profile";
    }
}
