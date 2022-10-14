package com.gaff.demo;

/*
 * This controller is for general sections of the site.
 * Last updated 10/14/2022
 * Author(s): Jessica Frank
 */

import com.gaff.demo.models.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String main(Model model) {
        //Get list of user roles
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        //Pass booleans to the home page that describe a user's roles
        boolean hasPlayerRole = auth.getAuthorities().stream()
          .anyMatch(r -> r.getAuthority().equals("ROLE_"+Role.player));
        model.addAttribute("hasPlayerRole", hasPlayerRole);
        boolean hasModRole = auth.getAuthorities().stream()
          .anyMatch(r -> r.getAuthority().equals("ROLE_"+Role.moderator));
        model.addAttribute("hasModRole", hasModRole);
        boolean hasAdminRole = auth.getAuthorities().stream()
          .anyMatch(r -> r.getAuthority().equals("ROLE_"+Role.admin));
        model.addAttribute("hasAdminRole", hasAdminRole);
        
        //Return the home page
        return "MainPage";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        return "Login";
    }
    
    @GetMapping("/login/again")
    public String loginAgain (Model model) {
        model.addAttribute("again", true);
        return "Login";
    }

}
