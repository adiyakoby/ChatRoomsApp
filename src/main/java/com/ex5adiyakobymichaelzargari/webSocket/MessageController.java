package com.ex5adiyakobymichaelzargari.webSocket;

import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.Services.MessageService;
import com.ex5adiyakobymichaelzargari.tabels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MessageController {

    Set<String> chatRoomNames = new HashSet<String>();

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

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

//        if (!chatRoomNames.contains(messge.chatrooName)) {
//            chatRoomNames.add(messge.chatrooName);
//        }

//        webSockets.forEach(
//                webSocket -> {
//                    if (webSocket.name === message.chatroomName) {
//                        return new MessageDTO(message.getFrom(), message.getText(), time);
//                    }
//                }
//        );
        chatRoomService.addMessageToChatRoom(user, "Home", message.getText(), time);
        return new MessageDTO(message.getFrom(), message.getText(), time);
    }
}
