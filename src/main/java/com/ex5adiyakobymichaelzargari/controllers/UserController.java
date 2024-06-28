package com.ex5adiyakobymichaelzargari.controllers;

import com.ex5adiyakobymichaelzargari.Services.UserService;
import org.springframework.ui.Model;
import com.ex5adiyakobymichaelzargari.tabels.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {



    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "signup";
        }
        userService.registerNewUser(user);
        return "index";
    }


}
