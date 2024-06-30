package com.ex5adiyakobymichaelzargari.controllers;

import com.ex5adiyakobymichaelzargari.Principals.MyUserPrincipal;
import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.UserDataSession;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoom;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * ChatRoomController handles the operations related to chat rooms, including displaying chat rooms and their messages.
 */
@Controller
@RequestMapping("/chatroom")
public class ChatRoomController {

    @Autowired
    private UserDataSession userDataSession;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private UserService userService;

    /**
     * Displays the specified chat room and its messages.
     *
     * @param principal the authenticated user
     * @param model the model to pass attributes to the view
     * @param id the ID of the chat room
     * @param redirectAttributes attributes for redirect scenarios
     * @return the view name of the chat room or a redirection in case of an error
     */
    @GetMapping("/{id}")
    public String chatroom(@AuthenticationPrincipal MyUserPrincipal principal, Model model, @PathVariable Long id ,RedirectAttributes redirectAttributes) {
        try {
            if (principal != null) {
                userDataSession.setChatId(id);
                model.addAttribute("userName", userDataSession.getUsername());
                model.addAttribute("messages", chatRoomService.getAllMessagesByChatRoom(id));
                model.addAttribute("chatRoom", new ChatRoom());
                model.addAttribute("currentChat", chatRoomRepository.findById(id).orElseThrow(() -> new Exception("Chat room not found")));
                model.addAttribute("chatRoomsList", userService.getUserChatRooms(principal.getUserId()));
                model.addAttribute("header", "chatroom");
            }

            return "/shared/chatroom";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "An unexpected error occurred. Please try again.");
            return "redirect:/chatroom/" + 1;
        }

    }

    /**
     * Displays the form to create a new chat room.
     *
     * @param model the model to pass attributes to the view
     * @return the view name of the new chat room form
     */
    @GetMapping("/newChatForm")
    public String newChatForm(@AuthenticationPrincipal MyUserPrincipal principal, Model model) {
        if (principal != null) {
            userDataSession.setUsername(principal.getUsername());
            model.addAttribute("userName", userDataSession.getUsername());
        }
        model.addAttribute("chatRoom", new ChatRoom());
        return "/shared/newChatForm";
    }
}
