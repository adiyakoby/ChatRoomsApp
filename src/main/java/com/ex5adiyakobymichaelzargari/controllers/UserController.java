package com.ex5adiyakobymichaelzargari.controllers;

import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.UserDataSession;
import com.ex5adiyakobymichaelzargari.tabels.UserRepository;
import org.springframework.ui.Model;
import com.ex5adiyakobymichaelzargari.tabels.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {

    @Autowired
    private UserDataSession userDataSession;


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public String addUser(@Valid User user, BindingResult result, Model model ,RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("errors", result.getAllErrors());
                return "signup";
            } else if (userRepository.findByUsername(user.getUsername()) != null) {
                model.addAttribute("errorSignUp", "Username already exists");
                return "signup";
            }
            userService.registerNewUser(user);
            userDataSession.setUsername(user.getUsername());
            return "index";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "An unexpected error occurred while trying to sign you up. Please try again.");
            return "redirect:/signup";
        }
    }

}
