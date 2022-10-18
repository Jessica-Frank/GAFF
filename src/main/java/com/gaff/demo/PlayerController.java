package com.gaff.demo;

/*
 * This controller is for player-specific sections of the site.
 * Last updated 10/15/2022
 * Author(s): Alec Droegemeier, Jessica Frank
 */

import com.gaff.demo.models.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlayerController {    
    @GetMapping("/game_list")
    public String getGameList(Model model) {
        return "GameList";
    }
    
    @GetMapping("/game_details")
    public String getGameDetails(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         boolean hasModRole = auth.getAuthorities().stream()
          .anyMatch(r -> r.getAuthority().equals("ROLE_"+Role.moderator));
        model.addAttribute("hasModRole", hasModRole);
        return "GameTemplate";
    }
    
    @GetMapping("/player_profile")
    public String playerProfile(Model model) {
        return "Profile";
    }
}
