package com.gaff.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author Jessica
 */

@Controller
public class AddGameController {
  @GetMapping("/add-game/details")
  public String addGameConfirm(Model model) {
    return "AddGameDetails";
  }
    
  @GetMapping("/add-game/search")
  public String addGameSearch(Model model) {
    return "AddGameSearch";
  }
    
}
