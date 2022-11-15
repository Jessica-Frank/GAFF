package com.gaff.demo;

/*
 * This controller is for general sections of the site.
 * Last updated 10/28/2022
 * Author(s): Jessica Frank
 */

import com.gaff.demo.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @Autowired
    UserRepository userRep;
    
    @GetMapping("/")
    public String getMainPage(Model model) {
        //Get list of user roles
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        //Pass booleans to the home page that describe a user's roles
        boolean hasPlayerRole = auth.getAuthorities().stream()
          .anyMatch(r -> r.getAuthority().equals("ROLE_"+"PLY"));
        model.addAttribute("hasPlayerRole", hasPlayerRole);
        boolean hasModRole = auth.getAuthorities().stream()
          .anyMatch(r -> r.getAuthority().equals("ROLE_"+"MOD"));
        model.addAttribute("hasModRole", hasModRole);
        boolean hasAdminRole = auth.getAuthorities().stream()
          .anyMatch(r -> r.getAuthority().equals("ROLE_"+"ADM"));
        model.addAttribute("hasAdminRole", hasAdminRole);
        
        boolean hasAnyRole = hasAdminRole || hasModRole || hasPlayerRole;
        model.addAttribute("hasAnyRole", hasAnyRole);
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        
        long profile_id = userRep.getUserByName(username).getId();
        model.addAttribute("profile_id", profile_id);
        //Return the home page
        return "MainPage";
    }
    
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "Login";
    }
    
    @GetMapping("/login/again")
    public String getLogoutPage (Model model) {
        model.addAttribute("again", true);
        return "Login";
    }

}
