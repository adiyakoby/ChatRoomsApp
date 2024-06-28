
package com.ex5adiyakobymichaelzargari.controllers;


import com.ex5adiyakobymichaelzargari.Principals.MyUserPrincipal;
import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoom;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import com.ex5adiyakobymichaelzargari.tabels.User;
import com.ex5adiyakobymichaelzargari.tabels.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class ChatRestController {

    @Autowired
    ChatRoomService chatRoomService;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/newChatRoom")
    public String newChatRoom(@AuthenticationPrincipal MyUserPrincipal principal, ChatRoom chatRoom) {
        chatRoomRepository.save(chatRoom);
        userService.addChatRoomToUser(principal.getUserId(), chatRoom.getId());

        return "redirect:/chatroom/" + chatRoom.getId();
    }

    @PostMapping("/addChatRoom")
    public String addChatRoom(@AuthenticationPrincipal MyUserPrincipal principal , ChatRoom chatroom) {
        ChatRoom chat = chatRoomRepository.findByName(chatroom.getName());
        if (chat == null) {
            return "redirect:/chatroom/" + 1;
        }
        userService.addChatRoomToUser(principal.getUserId(), chat.getId());
        return "redirect:/chatroom/" + chat.getId();
    }

    @PostMapping("/deleteChatRoom/{id}")
    public String deleteChatRoom(@AuthenticationPrincipal MyUserPrincipal principal , @PathVariable Long id) {
        System.out.println("[LOG]: deleteChatRoom " + id);
        if (principal != null) {
            userService.removeChatRoomFromUser(principal.getUserId(), id);
        }
        return "redirect:/chatroom/" + 1;
    }


}

