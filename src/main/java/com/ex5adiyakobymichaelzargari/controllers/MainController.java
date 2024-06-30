package com.ex5adiyakobymichaelzargari.controllers;

import com.ex5adiyakobymichaelzargari.Principals.MyUserPrincipal;

import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.UserDataSession;
import com.ex5adiyakobymichaelzargari.tabels.*;
import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {


    @Autowired
    private UserDataSession userDataSession;


    public MainController(ChatRoomService chatRoomService) {
    }

    /** Home page. */
    @RequestMapping("/")
    public String index(@AuthenticationPrincipal MyUserPrincipal principal, Model model){
        if(principal != null) {
            userDataSession.setUsername(principal.getUsername());
            userDataSession.setUserLoggedIn(true);
            model.addAttribute("userName", userDataSession.getUsername());
            model.addAttribute("userCurrentChat", userDataSession.getChatId());
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }


    /** Error page that displays all exceptions. */
    @GetMapping("/error")
    public String error(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    /** simple Error page. */
    @RequestMapping("/403")
    public String forbidden() {
        return "403";
    }


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex, Model model) {
        String errorMessage = (ex != null ? ex.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }


}