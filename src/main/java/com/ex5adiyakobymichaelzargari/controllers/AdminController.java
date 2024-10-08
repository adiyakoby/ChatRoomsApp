package com.ex5adiyakobymichaelzargari.controllers;

import com.ex5adiyakobymichaelzargari.AppConstants;
import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.UserDataSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * AdminController handles all administrative actions such as managing users and chat rooms.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private UserDataSession userDataSession;

    /**
     * Loads the admin dashboard with necessary data.
     *
     * @param model the model to add attributes to
     * @param redirectAttributes attributes for redirect scenarios
     * @return the admin dashboard view
     */
    @GetMapping("/dashboard")
    public String adminDashboard(Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("chatRooms", chatRoomService.findPendingChatRooms());
            model.addAttribute("users", userService.findAllUsers());
            model.addAttribute("enabledChatRooms", chatRoomService.findEnabledChatRooms());
            model.addAttribute("header", "Admin Dashboard");
            model.addAttribute("userCurrentChat", userDataSession.getChatId());
            model.addAttribute("userName", userDataSession.getUsername());


        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", AppConstants.ERR_GENERAL + e.getMessage());
            return "redirect:/error";
        }
        return "/admin/dashboard";
    }

    /**
     * Bans a user based on their ID.
     *
     * @param id the ID of the user to ban
     * @param redirectAttributes attributes for redirect scenarios
     * @return the redirect URL for the admin dashboard
     */
    @PostMapping("/banUser/{id}")
    public String banUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            if (id == 1L) {
                throw new RuntimeException(AppConstants.ADMIN_ERR_MAIN_ADMIN_BAN);
            }
            userService.banUser(id);
            redirectAttributes.addFlashAttribute("successMessage", AppConstants.ADMIN_SUCCESS_USER_BAN);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error banning user: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";

    }

    /**
     * Unbans a user based on their ID.
     *
     * @param id the ID of the user to unban
     * @param redirectAttributes attributes for redirect scenarios
     * @return the redirect URL for the admin dashboard
     */
    @PostMapping("/unbanUser/{id}")
    public String unbanUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.unbanUser(id);
            redirectAttributes.addFlashAttribute("successMessage", AppConstants.ADMIN_SUCCESS_USER_UNBAN);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error unbanning user: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";

    }

    /**
     * Approves a chat room based on its ID.
     *
     * @param id the ID of the chat room to approve
     * @param redirectAttributes attributes for redirect scenarios
     * @return the redirect URL for the admin dashboard
     */
    @PostMapping("/approveChatRoom/{id}")
    public String approveChatRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            chatRoomService.approveChatRoom(id);
            redirectAttributes.addFlashAttribute("successMessage", AppConstants.ADMIN_SUCCESS_CHATROOM_APPROVE);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error approving chat room: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    /**
     * Disapproves a chat room based on its ID.
     *
     * @param id the ID of the chat room to disapprove
     * @param redirectAttributes attributes for redirect scenarios
     * @return the redirect URL for the admin dashboard
     */
    @PostMapping("/disapproveChatRoom/{id}")
    public String disapproveChatRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            chatRoomService.disapproveChatRoom(id);
            redirectAttributes.addFlashAttribute("successMessage", AppConstants.ADMIN_SUCCESS_CHATROOM_DISAPPROVE);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error disapproving chat room: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";

    }

    /**
     * Deletes a chat room based on its ID.
     *
     * @param id the ID of the chat room to delete
     * @param redirectAttributes attributes for redirect scenarios
     * @return the redirect URL for the admin dashboard
     */
    @PostMapping("/deleteChatRoom/{id}")
    public String deleteChatRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            if (id == 1L) {
                throw new RuntimeException(AppConstants.ADMIN_ERR_MAIN_CHAT_DELETE);
            }
            if (userDataSession.getChatId() == id) {
                userDataSession.setChatId(1L);
            }
            chatRoomService.deleteChatRoom(id);
            redirectAttributes.addFlashAttribute("successMessage", AppConstants.ADMIN_SUCCESS_CHATROOM_DELETE);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting chat room: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    /**
     * Enables a chat room based on its ID.
     *
     * @param id the ID of the chat room to enable
     * @param redirectAttributes attributes for redirect scenarios
     * @return the redirect URL for the admin dashboard
     */
    @PostMapping("/enableChatRoom/{id}")
    public String enableChatRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            chatRoomService.enableChatRoom(id);
            redirectAttributes.addFlashAttribute("successMessage", AppConstants.ADMIN_SUCCESS_CHATROOM_ENABLE);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error enabling chat room: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    /**
     * Disables a chat room based on its ID.
     *
     * @param id the ID of the chat room to disable
     * @param redirectAttributes attributes for redirect scenarios
     * @return the redirect URL for the admin dashboard
     */
    @PostMapping("/disableChatRoom/{id}")
    public String disableChatRoom(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            if (id == 1L) {
                throw new RuntimeException(AppConstants.ADMIN_ERR_MAIN_CHAT_DISABLE);
            }
            if (userDataSession.getChatId() == id) {
                userDataSession.setChatId(1L);
            }
            chatRoomService.disableChatRoom(id);
            redirectAttributes.addFlashAttribute("successMessage", AppConstants.ADMIN_SUCCESS_CHATROOM_DISABLE);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error disabling chat room: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";

    }

}
