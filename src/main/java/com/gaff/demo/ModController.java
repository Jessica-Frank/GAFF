package com.gaff.demo;

/*
 * This controller is for moderator-specific sections of the site.
 * Last updated 10/15/2022
 * Author(s): Jessica Frank
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModController {    
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
    
    @GetMapping("/edit_game")
    public String editGame(Model model) {
        return "EditGameDetails";
    }
    
    @PostMapping("/add_game/submit")
    public String addGameSubmit(Model model, @RequestParam String name, 
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
        return "AddGameConfirmation";
    }
}
