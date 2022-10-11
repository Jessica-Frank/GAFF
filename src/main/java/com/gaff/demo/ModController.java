package com.gaff.demo;

/*
 * This controller is for moderator-specific sections of the site.
 * Last updated 10/9/2022
 * Author(s): Jessica Frank
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ModController {    
    @GetMapping("/add_game/search")
    public String addGameSearch(Model model) {
        return "AddGameSearch";
    }
    
    @PostMapping("/add_game/details")
    public String addGameConfirm(Model model) {
        return "AddGameDetails";
    }
    
    @PostMapping("/add_game/submit")
    public String addGameSubmit(Model model) {
        return "AddGameConfirmation";
    }
}
