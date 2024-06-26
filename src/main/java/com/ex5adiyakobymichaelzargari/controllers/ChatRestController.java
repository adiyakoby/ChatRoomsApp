package com.ex5adiyakobymichaelzargari.controllers;


import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import com.ex5adiyakobymichaelzargari.tabels.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController()
@RequestMapping(value="/api")
public class ChatRestController {

    @Autowired
    ChatRoomService chatRoomService;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @GetMapping(value="/Messages/{chatRoomName}")
    public List<Message> getAllMessages(@PathVariable String chatRoomName) {
        System.out.println("[LOG] : got Request for getAllMessages()");
        return chatRoomService.getAllMessagesByChatRoom(chatRoomRepository.findByName(chatRoomName).getId());
    }
}
