package com.ex5adiyakobymichaelzargari.controllers;

import com.ex5adiyakobymichaelzargari.Principals.MyUserPrincipal;
import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoom;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class ChatRestController {

    @Autowired
    ChatRoomService chatRoomService;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    UserService userService;


    @PostMapping("/newChatRoom")
    public String newChatRoom(@AuthenticationPrincipal MyUserPrincipal principal, ChatRoom chatRoom, RedirectAttributes redirectAttributes) {
        try {
            chatRoomRepository.save(chatRoom);
            redirectAttributes.addFlashAttribute("message", "Your request for a new chat room has been submitted successfully and is pending approval.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "An error occurred while submitting your request. Please try again.");
        }
        return "redirect:/chatroom/newChatForm";
    }

    @GetMapping("/newChatRoom")
    public String newChatRoom() {
        return "redirect:/chatroom/newChatForm";
    }

    @PostMapping("/addChatRoom")
    public String addChatRoom(@AuthenticationPrincipal MyUserPrincipal principal , ChatRoom chatroom, RedirectAttributes redirectAttributes) {
        try {
            ChatRoom chat = chatRoomService.getChatRoomByName(chatroom.getName());
            if (chat == null || chat.isChatFull()) {
                String errMsg = chat == null ? "chat room not found or not approved yet." : "Chat room is full. You cannot join.";
                redirectAttributes.addFlashAttribute("errorAddChatRoom", errMsg);
                return "redirect:/chatroom/" + 1;
            }
            userService.addChatRoomToUser(principal.getUserId(), chat.getId());
            return "redirect:/chatroom/" + chat.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorAddChatRoom", "An unexpected error occurred. Please try again.");
            return "redirect:/chatroom/" + 1;
        }

    }

    @GetMapping("/addChatRoom")
    public String addChatRoom() {
        return "redirect:/";
    }

    @PostMapping("/deleteChatRoom/{id}")
    public String deleteChatRoom(@AuthenticationPrincipal MyUserPrincipal principal , @PathVariable Long id ,RedirectAttributes redirectAttributes) {
        try{
            if (principal != null) {
                userService.removeChatRoomFromUser(principal.getUserId(), id);
            }
            return "redirect:/chatroom/" + 1;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "An unexpected error occurred while trying to delete the chatroom. Please try again.");
            return "redirect:/chatroom/" + 1;
        }

    }

    @GetMapping("/deleteChatRoom/{id}")
    public String deleteChatRoom() {
        return "redirect:/";
    }
}

