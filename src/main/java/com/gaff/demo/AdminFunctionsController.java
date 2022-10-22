package com.gaff.demo;

/*
 * This controller is for admin-only sections of the site.
 * Last updated 10/9/2022
 * Author(s): Alex Wesley, Jessica Frank
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminFunctionsController {
  
  @GetMapping("/change_role")
  public String PromoteDemoteMod(Model model) {
    return "PromoteDemoteMod";
  }
  
  @PostMapping("/change_role")
  public String PromoteDemoteModMssg(Model model, @RequestParam String nameVar, @RequestParam String userType) {
      model.addAttribute("again", true);
      model.addAttribute("name", nameVar);
      model.addAttribute("role", userType);
    return "PromoteDemoteMod";
  }
  @GetMapping("/view_logs")
  public String ViewSystemLogs (Model model) {
      return "ViewSystemLogs";
  }
    
}