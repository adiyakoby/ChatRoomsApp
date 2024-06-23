package com.ex5adiyakobymichaelzargari.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    /** Home page. */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/chatroom")
    public String chatroom() {
        return "chatroom";
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

    @PostMapping("/login")
    public void login(Principal principal) {
        System.out.println(principal);
        System.out.println(principal.getName());

    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex, Model model) {

        //logger.error("Exception during execution of SpringSecurity application", ex);
        String errorMessage = (ex != null ? ex.getMessage() : "Unknown error");

        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }


}