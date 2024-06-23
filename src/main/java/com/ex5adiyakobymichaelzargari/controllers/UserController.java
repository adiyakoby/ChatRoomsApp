package com.ex5adiyakobymichaelzargari.controllers;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.tabels.User;
import com.ex5adiyakobymichaelzargari.tabels.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    UserDetailsService userDetailsService;


//    @GetMapping("/login-check")
//    public String loginCheck(Model model, Principal principal) {
//        System.out.println("Login check");
//        System.out.println(principal.getName());
//        return "login";
//    }

    @PostMapping("/signup")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        // validate the object and get the errors
        if (result.hasErrors()) {
            // errors MUST be displayed in the view and not just printed to the console
            System.out.println("validation errors: " + result.getAllErrors());
            model.addAttribute("errors", result.getAllErrors());
            return "signup";
        }
        userRepository.save(user);
        return "index";
    }

    @PostMapping("/login")
    public String login(@Valid User user, BindingResult result, Model model) {
        System.out.println("login");
        if (result.hasErrors()) {
            System.out.println("validation errors: " + result.getAllErrors());
            model.addAttribute("errors", result.getAllErrors());
            return "login";
        }

        model.addAttribute("user", user);
        return "index";
    }

//    @GetMapping("/adduser")
//    public String showAddUserForm(User user, Model model) {
//        // redirect to main page
//        return "redirect:/";
//    }

  /*  *//** handling IllegalArgumentException *//*
    @GetMapping("/error")
    public String error() {
        return "error";
    }*/


}
