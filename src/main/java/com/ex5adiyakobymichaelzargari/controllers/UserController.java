package com.ex5adiyakobymichaelzargari.controllers;

import com.ex5adiyakobymichaelzargari.Services.MyUserDetailsService;
import com.ex5adiyakobymichaelzargari.Services.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
//import com.ex5adiyakobymichaelzargari.Services.UserService;
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



    @PostMapping("/signup")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        // validate the object and get the errors
        if (result.hasErrors()) {
            // errors MUST be displayed in the view and not just printed to the console
            System.out.println("validation errors: " + result.getAllErrors());
            model.addAttribute("errors", result.getAllErrors());
            return "signup";
        }
        userService.registerNewUser(user);
        return "index";
    }


}
