package com.ex5adiyakobymichaelzargari.controllers;

import com.ex5adiyakobymichaelzargari.Principals.MyUserPrincipal;
import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.UserDataSession;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoom;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import com.ex5adiyakobymichaelzargari.tabels.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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


    @GetMapping("/{id}")
    public String chatroom(@AuthenticationPrincipal MyUserPrincipal principal, Model model, @PathVariable Long id) {
        if (principal != null) {
            userDataSession.setChatId(id);
            List<Message> messages = chatRoomService.getAllMessagesByChatRoom(id);
            model.addAttribute("userName", userDataSession.getUsername());
            model.addAttribute("messages", messages);
            model.addAttribute("chatRoom", new ChatRoom());
            model.addAttribute("currentChat", chatRoomRepository.findById(id).get());
            model.addAttribute("chatRoomsList", userService.getUserChatRooms(principal.getUserId()));
            model.addAttribute("header", "chatroom");
        }

        return "/shared/chatroom";
    }

    @GetMapping("/newChatForm")
    public String newChatForm(Model model) {
        model.addAttribute("chatRoom", new ChatRoom());
        return "/shared/newChatForm";
    }
}
