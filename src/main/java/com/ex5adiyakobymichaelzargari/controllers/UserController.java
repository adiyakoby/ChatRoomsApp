package com.ex5adiyakobymichaelzargari.controllers;

import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.UserDataSession;
import com.ex5adiyakobymichaelzargari.tabels.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.ui.Model;
import com.ex5adiyakobymichaelzargari.tabels.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * UserController handles user-related actions such as signing up and logging in.
 */
@Controller
public class UserController {

    @Autowired
    private UserDataSession userDataSession;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Handles the user signup process.
     *
     * @param user the user to be registered
     * @param result the binding result for validation
     * @param model the model to pass attributes to the view
     * @param redirectAttributes attributes for redirect scenarios
     * @return the view name to be rendered
     */
    @PostMapping("/signup")
    public String addUser(@Valid User user, BindingResult result, Model model ,RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("errors", result.getAllErrors());
                return "public/signup";
            }

            userService.registerNewUser(user);
            userDataSession.setUsername(user.getUsername());
            redirectAttributes.addFlashAttribute("headerSuccessMessage", "You have been registered successfully");
            return "redirect:/login";

        } catch (EntityExistsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/signup";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An unexpected error occurred while trying to sign you up. Please try again.");
            return "redirect:/signup";
        }
    }

}
