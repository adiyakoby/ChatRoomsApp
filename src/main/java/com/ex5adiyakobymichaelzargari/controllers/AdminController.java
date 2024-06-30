package com.ex5adiyakobymichaelzargari.controllers;

import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import com.ex5adiyakobymichaelzargari.tabels.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatRoomService chatRoomService;


    @PostMapping("/banUser/{id}")
    public String banUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.banUser(id);
        redirectAttributes.addFlashAttribute("successMessage", "User has been banned.");
        return "redirect:/adminDashboard";
    }

    @PostMapping("/unbanUser/{id}")
    public String unbanUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.unbanUser(id);
        redirectAttributes.addFlashAttribute("successMessage", "User has been unbanned.");
        return "redirect:/adminDashboard";
    }


    @PostMapping("/approveChatRoom/{id}")
    public String approveChatRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        chatRoomService.approveChatRoom(id);
        redirectAttributes.addFlashAttribute("successMessage", "Chat room has been approved.");
        return "redirect:/adminDashboard";
    }

    @PostMapping("/disapproveChatRoom/{id}")
    public String disapproveChatRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        chatRoomService.disapproveChatRoom(id);
        redirectAttributes.addFlashAttribute("successMessage", "Chat room has been disapproved.");
        return "redirect:/adminDashboard";
    }

    @PostMapping("/deleteChatRoom/{id}")
    public String deleteChatRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        chatRoomService.deleteChatRoom(id);
        redirectAttributes.addFlashAttribute("successMessage", "Chat room has been deleted.");
        return "redirect:/adminDashboard";
    }


    @PostMapping("/enableChatRoom/{id}")
    public String enableChatRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        chatRoomService.enableChatRoom(id);
        redirectAttributes.addFlashAttribute("successMessage", "Chat room has been enabled.");
        return "redirect:/adminDashboard";
    }

    @PostMapping("/disableChatRoom/{id}")
    public String disableChatRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        chatRoomService.disableChatRoom(id);
        redirectAttributes.addFlashAttribute("successMessage", "Chat room has been disabled.");
        return "redirect:/adminDashboard";
    }

    @PostMapping("/removeChatRoom/{id}")
    public String removeChatRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        chatRoomService.deleteChatRoom(id);
        redirectAttributes.addFlashAttribute("successMessage", "Chat room has been removed.");
        return "redirect:/adminDashboard";
    }
}
