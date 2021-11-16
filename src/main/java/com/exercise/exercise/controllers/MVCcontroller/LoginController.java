package com.exercise.exercise.controllers.MVCcontroller;

import com.exercise.exercise.model.user.User;
import com.exercise.exercise.service.MVCService.MVCUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    private final MVCUserService mvcUserService;

    @Autowired
    public LoginController(MVCUserService mvcUserService) {
        this.mvcUserService = mvcUserService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        return "register";
    }

    @PostMapping("/register")
    public String add(@ModelAttribute User user) {
        mvcUserService.save(user);
        return "redirect:/";
    }
}
