package com.ex5adiyakobymichaelzargari.webSocket;

import com.ex5adiyakobymichaelzargari.tabels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessageController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message send(MessageDTO message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        User user = userRepository.findByUsername(message.getFrom());

        return new Message(message.getText(), user, chatRoomRepository.findByName("Home"), time); // String message, User user, ChatRoom chatRoom, Date date
    }

}
