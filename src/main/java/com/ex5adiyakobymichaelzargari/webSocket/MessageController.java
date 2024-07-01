package com.ex5adiyakobymichaelzargari.webSocket;

import com.ex5adiyakobymichaelzargari.AppConstants;
import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
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
    private ChatRoomService chatRoomService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    /**
     * Sends a message to a chat room via the websocket.
     *
     * @param message the message to be sent
     */
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public void send(MessageDTO message) {
        try {
            String time = new SimpleDateFormat("HH:mm").format(new Date());
            User user = userRepository.findByUsername(message.getFrom());
            ChatRoom chatroom = chatRoomRepository.findById(message.getChatId()).orElse(null);

            if (chatroom == null || !chatroom.isEnabled()) {
                String errorDestination = AppConstants.SOCKET_ERROR_PATH  + message.getFrom();
                messagingTemplate.convertAndSend(errorDestination, AppConstants.SOCKET_ERR_MSG_DIS_REV);
                return;
            }

            if (user.getRole().equals(AppConstants.ROLE_BANNED)) {
                String errorDestination = AppConstants.SOCKET_ERROR_PATH  + message.getFrom();
                messagingTemplate.convertAndSend(errorDestination, AppConstants.SOCKET_ERR_MSG_USER_BANNED);
                return;
            }

            chatRoomService.addMessageToChatRoom(user, chatroom.getId(), message.getText(), time);

            String destination = AppConstants.SOCKET_CHAT_PATH + message.getChatId();
            messagingTemplate.convertAndSend(destination , new MessageDTO(message.getFrom(), message.getText(), time));
        } catch (Exception e) {
            String errorDestination = AppConstants.SOCKET_ERROR_PATH + message.getFrom();
            messagingTemplate.convertAndSend(errorDestination, AppConstants.SOCKET_ERR_MSG_GENERAL + e.getMessage());
        }

    }
}

