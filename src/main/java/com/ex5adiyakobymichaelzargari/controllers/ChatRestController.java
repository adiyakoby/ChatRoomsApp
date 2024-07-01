package com.ex5adiyakobymichaelzargari.controllers;

import com.ex5adiyakobymichaelzargari.AppConstants;
import com.ex5adiyakobymichaelzargari.Principals.MyUserPrincipal;
import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoom;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * ChatRestController handles chat room related actions such as creating, adding, and deleting chat rooms.
 */
@Controller
public class ChatRestController {

    @Autowired
    ChatRoomService chatRoomService;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    UserService userService;

    /**
     * Handles the creation of a new chat room.
     *
     * @param principal the authenticated user
     * @param chatroom the chat room to be created
     * @param redirectAttributes attributes for redirect scenarios
     * @return the redirect URL for the new chat room form
     */
    @PostMapping("/newChatRoom")
    public String newChatRoom(@AuthenticationPrincipal MyUserPrincipal principal, ChatRoom chatroom, RedirectAttributes redirectAttributes) {
        try {
            chatRoomService.createChatRoom(chatroom);
            redirectAttributes.addFlashAttribute("successMessage", AppConstants.SUCCESS_CHAT_FORM_SENT);
        } catch (EntityExistsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", AppConstants.ERR_SIGNUP_UNEXPECTED);
        }
        return "redirect:/chatroom/newChatForm";
    }

    /**
     * Redirects to the new chat room form.
     *
     * @return the redirect URL for the new chat room form
     */
    @GetMapping("/newChatRoom")
    public String newChatRoom() {
        return "redirect:/chatroom/newChatForm";
    }

    /**
     * Handles adding a user to an existing chat room.
     *
     * @param principal the authenticated user
     * @param chatroom the chat room to be added
     * @param redirectAttributes attributes for redirect scenarios
     * @return the redirect URL for the chat room or an error page
     */
    @PostMapping("/addChatRoom")
    public String addChatRoom(@AuthenticationPrincipal MyUserPrincipal principal , ChatRoom chatroom, RedirectAttributes redirectAttributes) {
        try {
            ChatRoom chat = chatRoomService.getChatRoomByName(chatroom.getName());
            if (chat == null || chat.isChatFull()) {
                String errMsg = chat == null ? AppConstants.ERR_CHATROOM_NOT_FOUND : AppConstants.ERR_CHATROOM_FULL;
                redirectAttributes.addFlashAttribute("errorAddChatRoom", errMsg);
                return "redirect:/chatroom/" + 1;
            }
            userService.addChatRoomToUser(principal.getUserId(), chat.getId());
            return "redirect:/chatroom/" + chat.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorAddChatRoom", AppConstants.ERR_GENERAL);
            return "redirect:/chatroom/" + 1;
        }

    }

    /**
     * Redirects to the add chat room form.
     *
     * @return the redirect URL for the add chat room form
     */
    @GetMapping("/addChatRoom")
    public String addChatRoom() {
        return "redirect:/";
    }

    /**
     * Handles deleting a chat room from a user's list.
     *
     * @param principal the authenticated user
     * @param id the ID of the chat room to delete
     * @param redirectAttributes attributes for redirect scenarios
     * @return the redirect URL for the chat room list or an error page
     */
    @PostMapping("/removeChatRoom/{id}")
    public String deleteChatRoom(@AuthenticationPrincipal MyUserPrincipal principal , @PathVariable Long id ,RedirectAttributes redirectAttributes) {
        try{
            if (principal != null) {
                userService.removeChatRoomFromUser(principal.getUserId(), id);
            }
            return "redirect:/chatroom/" + 1;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", AppConstants.ERR_DELETE_CHATROOM_UNEXPECTED);
            return "redirect:/chatroom/" + 1;
        }

    }

    /**
     * Redirects to the delete chat room form.
     *
     * @return the redirect URL for the delete chat room form
     */
    @GetMapping("/deleteChatRoom/{id}")
    public String deleteChatRoom() {
        return "redirect:/";
    }
}

