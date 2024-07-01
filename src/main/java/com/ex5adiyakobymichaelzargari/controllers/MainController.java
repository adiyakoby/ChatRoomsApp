package com.ex5adiyakobymichaelzargari.controllers;

import com.ex5adiyakobymichaelzargari.AppConstants;
import com.ex5adiyakobymichaelzargari.Principals.MyUserPrincipal;

import com.ex5adiyakobymichaelzargari.UserDataSession;
import com.ex5adiyakobymichaelzargari.tabels.*;
import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * MainController handles the main page, login, signup, and error pages.
 */
@Controller
public class MainController {

    @Autowired
    private UserDataSession userDataSession;

    public MainController(ChatRoomService chatRoomService) {
    }

    /**
     * Displays the home page.
     *
     * @param principal the authenticated user
     * @param model the model to pass attributes to the view
     * @return the view name of the home page
     */
    @RequestMapping("/")
    public String index(@AuthenticationPrincipal MyUserPrincipal principal, Model model){
        if(principal != null) {
            userDataSession.setUsername(principal.getUsername());
            userDataSession.setUserLoggedIn(true);
            model.addAttribute("userName", userDataSession.getUsername());
            model.addAttribute("userCurrentChat", userDataSession.getChatId());
            model.addAttribute("header", "Home");
        }
        return "public/index";
    }

    /**
     * Displays the login page.
     *
     * @return the view name of the login page
     */
    @GetMapping("/login")
    public String login() {
        return "public/login";
    }

    /**
     * Displays the signup page.
     *
     * @param model the model to pass attributes to the view
     * @return the view name of the signup page
     */
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "public/signup";
    }


    /**
     * Displays the error page.
     *
     * @param ex the exception that occurred
     * @param model the model to pass attributes to the view
     * @return the view name of the error page
     */
    @GetMapping("/error")
    public String error(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "public/error";
    }

    /**
     * Displays the banned user page.
     *
     * @return the view name of the banned user page
     */
    @GetMapping("/banned")
    public String error() {
        return "public/BannedUser";
    }

    /**
     * Displays the forbidden page.
     *
     * @return the view name of the forbidden page
     */
    @RequestMapping("/403")
    public String forbidden() {
        return "public/403";
    }

    /**
     * Handles exceptions and displays the error page.
     *
     * @param ex the exception that occurred
     * @param model the model to pass attributes to the view
     * @return the view name of the error page
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex, Model model) {
        String errorMessage = (ex != null ? ex.getMessage() : AppConstants.ERR_GENERAL);
        model.addAttribute("errorMessage", errorMessage);
        return "public/error";
    }


}