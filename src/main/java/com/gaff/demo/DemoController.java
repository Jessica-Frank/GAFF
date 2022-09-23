package com.gaff.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author Jessica
 */
@Controller
public class DemoController {
    @GetMapping("/")
    public String main(Model model) {
        return "MainPage";
    }

}
