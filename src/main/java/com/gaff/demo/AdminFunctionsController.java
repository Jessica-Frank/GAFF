package com.gaff.demo;

/*
 * This controller is for admin-only sections of the site.
 * Last updated 10/9/2022
 * Author(s): Alex Wesley, Jessica Frank
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminFunctionsController {
  
  @GetMapping("/change_role")
  public String PromoteDemoteMod(Model model) {
    return "PromoteDemoteMod";
  }
  
  @GetMapping("/view_logs")
  public String ViewSystemLogs (Model model) {
      return "ViewSystemLogs";
  }
    
}