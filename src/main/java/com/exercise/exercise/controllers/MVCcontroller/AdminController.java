package com.exercise.exercise.controllers.MVCcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String showLoginForm(){
        return "index";
    }
}
