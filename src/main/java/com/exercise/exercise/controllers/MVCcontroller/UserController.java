package com.exercise.exercise.controllers.MVCcontroller;


import com.exercise.exercise.dto.user.UserRequest;
import com.exercise.exercise.model.user.User;
import com.exercise.exercise.service.MVCService.MVCUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

// mvc controller
@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final MVCUserService MVCUserService;

    @Autowired
    public UserController(MVCUserService MVCUserService) {
        this.MVCUserService = MVCUserService;
    }

    // http://localhost:8080/users

    // map url to controller method


    @GetMapping("/admin/users")
    public String showUsersPage(Model model) {
        // return a html page with users
        // add list of users
        List<User> users = MVCUserService.findAll();
        model.addAttribute("usersInView", users);

        // resolved by the view resolver
        return "users-list";
    }

    @GetMapping("/admin/users/add")
    public String showAddFrom(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        return "user-add";
    }

    @PostMapping("/admin/users/add")
    public String add(@ModelAttribute User user) {
        MVCUserService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}/edit")
    public String showEditForm(Model model,
                               @PathVariable Long id) {

        model.addAttribute("user", MVCUserService.findById(id));
        return "user-edit";
    }

    public ModelAndView showEditForm2(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("user-edit");
        modelAndView.addObject("user", MVCUserService.findById(id));
        return modelAndView;
    }

    @PostMapping("/admin/users/{id}/edit")
    public String edit(
            @PathVariable Long id,
            @ModelAttribute UserRequest userData) {

        MVCUserService.update(id, userData);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}/delete")
    public String delete(@PathVariable long id) {
        MVCUserService.delete(id);
        return "redirect:/admin/users";
    }
}
