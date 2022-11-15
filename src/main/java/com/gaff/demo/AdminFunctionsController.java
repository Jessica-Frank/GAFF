package com.gaff.demo;

/*
 * This controller is for admin-only sections of the site. 
 * Last updated 10/14/2022. 
 * Author(s): Alex Wesley, Jessica Frank
 */
import com.gaff.demo.models.ActionRepository;
import com.gaff.demo.models.Actions;
import com.gaff.demo.models.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminFunctionsController {

    @Autowired
    ActionRepository actionRep;
    @Autowired
    UserRepository userRep;

    @GetMapping("/change_role")
    public String PromoteDemoteMod(Model model) {
        return "PromoteDemoteMod";
    }

    @PostMapping("/change_role")
    public String PromoteDemoteModMssg(Model model, @RequestParam String nameVar, @RequestParam String userType) {
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        
        if (userType.equals("Moderator")) {
            actionRep.addAction(nameVar + " was promoted to a Moderator ", "Admin");
            userRep.changeUserRole(nameVar, "MOD");
        } else if (userType.equals("Player")) {
            actionRep.addAction(nameVar + " was demoted to a Player ", "Admin");
            userRep.changeUserRole(nameVar, "PLY");
        } 
        
        model.addAttribute("again", true);
        model.addAttribute("name", nameVar);
        model.addAttribute("role", userType);
        return "PromoteDemoteMod";
    }

    @GetMapping("/view_logs")
    public String ViewSystemLogs(Model model) {
        List<Actions> actions = actionRep.getAllActions();
        model.addAttribute("actionList", actions);
        return "ViewSystemLogs";
    }

}
