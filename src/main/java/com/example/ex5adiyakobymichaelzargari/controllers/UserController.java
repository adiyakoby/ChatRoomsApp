package com.example.ex5adiyakobymichaelzargari.controllers;

import ch.qos.logback.core.model.Model;
import com.example.ex5adiyakobymichaelzargari.tabels.User;
import com.example.ex5adiyakobymichaelzargari.tabels.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String index(User user, Model model) {
        // need to write
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user, Model model) {

        // need to write
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, org.springframework.ui.Model model) {
            // need to write
//        // validate the object and get the errors
//        if (result.hasErrors()) {
//            // errors MUST be displayed in the view and not just printed to the console
//            System.out.println("validation errors: " + result.getAllErrors());
//            return "add-user";
//        }

//        repository.save(user);

        // pass the list of users to the view
//        model.addAttribute("users", repository.findAll());
        return "index";
    }

    @GetMapping("/adduser")
    public String showAddUserForm(User user, Model model) {
        // redirect to main page
        return "redirect:/";
    }

    /** handling IllegalArgumentException */
    @GetMapping("/error")
    public String error() {
        return "error";
    }


}
