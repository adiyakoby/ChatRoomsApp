package com.ex5adiyakobymichaelzargari.webSocket;

import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.Services.MessageService;
import com.ex5adiyakobymichaelzargari.tabels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MessageController {

    Set<String> webSockets = new HashSet<String>();

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    //    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public Message send(MessageDTO message) throws Exception {
//        String time = new SimpleDateFormat("HH:mm").format(new Date());
//        User user = userRepository.findByUsername(message.getFrom());
//
//        return new Message(message.getText(), user, chatRoomRepository.findByName("Home"), time); // String message, User user, ChatRoom chatRoom, Date date
//    }
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public MessageDTO send(MessageDTO message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        User user = userRepository.findByUsername(message.getFrom());

        chatRoomService.addMessageToChatRoom(user, "Home", message.getText(), time);
//        System.out.println(chatRoomService.getAllMessagesByChatRoom(1L));

//        messageService.registerNewMessage(message.getText(), message.getFrom(), "Home");
//        chatRoomRepository.findByName("Home").addMessage(new Message(message.getText(), user, chatRoomRepository.findByName("Home"), time));
        return new MessageDTO(message.getFrom(), message.getText());
    }
}
