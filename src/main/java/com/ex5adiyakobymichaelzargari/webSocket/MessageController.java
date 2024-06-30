package com.ex5adiyakobymichaelzargari.webSocket;

import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.Services.MessageService;
import com.ex5adiyakobymichaelzargari.tabels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MessageController handles the sending of messages in the websocket.
 */
@Controller
public class MessageController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Sends a message to a chat room via the websocket.
     *
     * @param message the message to be sent
     * @throws Exception if the message cannot be sent
     */
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public void send(MessageDTO message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        User user = userRepository.findByUsername(message.getFrom());

        chatRoomService.addMessageToChatRoom(user, message.getChatId(), message.getText(), time);
        String destination = "/topic/chatroom/" + message.getChatId();

        messagingTemplate.convertAndSend(destination , new MessageDTO(message.getFrom(), message.getText(), time));

    }
}
