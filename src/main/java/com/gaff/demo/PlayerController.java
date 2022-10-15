/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gaff.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Alec
 */

@Controller
public class PlayerController {
  
  @GetMapping("/game_list")
  public String gameList(Model model) {
    return "GameList";
  }
   @GetMapping("/game_list/game")
  public String game(Model model) {
    return "GameTemplate";
  }
    @GetMapping("/player_profile")
  public String playerProfile(Model model) {
    return "Profile";
  }
}
