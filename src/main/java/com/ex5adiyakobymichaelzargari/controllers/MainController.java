package com.ex5adiyakobymichaelzargari.controllers;

import com.ex5adiyakobymichaelzargari.tabels.ChatRoom;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.tabels.Message;
import com.ex5adiyakobymichaelzargari.tabels.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomRepository chatRoomRepository;

    public MainController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    /** Home page. */
    @RequestMapping("/")
    public String index() {
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


    @GetMapping("/chatroom/{id}")
    public String chatroom(Principal principal, Model model, @PathVariable Long id) {
        if (principal != null) {
            List<Message> messages = chatRoomService.getAllMessagesByChatRoom(id);
//            List<Message> messages = chatRoomService.getAllMessagesByChatRoom("Home"); //chatRoomRepository.findByName("Home").getMessages();
            model.addAttribute("username", principal.getName());
            model.addAttribute("messages", messages);
            model.addAttribute("chatRoom", new ChatRoom());
            model.addAttribute("currentChat", chatRoomRepository.findById(id).get());
            model.addAttribute("chatRoomsList", chatRoomRepository.findAll());

        }

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


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex, Model model) {

        //logger.error("Exception during execution of SpringSecurity application", ex);
        String errorMessage = (ex != null ? ex.getMessage() : "Unknown error");

        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }


}