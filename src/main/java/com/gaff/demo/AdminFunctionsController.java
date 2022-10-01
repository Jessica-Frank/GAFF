package com.gaff.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Alex Wesley
 */

@Controller
public class AdminFunctionsController {
  
  @GetMapping("/Promote/Demote_Mod")
  public String PromoteDemoteMod(Model model) {
    return "PromoteDemoteMod";
  }
  
  @GetMapping("/View_System_Logs")
  public String ViewSystemLogs (Model model) {
      return "ViewSystemLogs";
  }
    
}