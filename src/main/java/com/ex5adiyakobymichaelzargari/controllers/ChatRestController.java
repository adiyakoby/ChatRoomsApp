
package com.ex5adiyakobymichaelzargari.controllers;


import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoom;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class ChatRestController {

    @Autowired
    ChatRoomService chatRoomService;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @PostMapping("/newChatRoom")
    public String newChatRoom(ChatRoom chatRoom) {
        System.out.println("New ChatRoom: " + chatRoom.getName());
        chatRoomRepository.save(chatRoom);
        return "redirect:/chatroom/" + chatRoom.getId();
    }

}

