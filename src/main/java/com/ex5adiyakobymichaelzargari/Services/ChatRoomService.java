package com.ex5adiyakobymichaelzargari.Services;

import com.ex5adiyakobymichaelzargari.tabels.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MessageRepository messageRepository;

    public ChatRoom createChatRoom(String name, String description, boolean isEnabled) {
        if (chatRoomRepository.findByName(name) != null) {
            return null;
        }
        ChatRoom chatroom = new ChatRoom();
        chatroom.setName(name);
        chatroom.setDescription(description);
        chatroom.setEnabled(isEnabled);
        return chatRoomRepository.save(chatroom);
    }

    public ChatRoom enableChatRoom(Long Id) {
        ChatRoom chatroom = chatRoomRepository.findById(Id).get();
        chatroom.setEnabled(true);
        return chatRoomRepository.save(chatroom);
    }

    public List<ChatRoom> getAllChatRooms() {

        return chatRoomRepository.findAll();
    }

    public ChatRoom getChatRoomById(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElse(null);
        if (chatRoom == null) {
            return chatRoomRepository.findById(1L).orElse(null);
        }
        return chatRoom;
    }

    public ChatRoom getChatRoomByName(String chatRoomName) {
        ChatRoom chatroom = chatRoomRepository.findByName(chatRoomName);
        if (chatroom == null || !chatroom.isEnabled()) {
            return null;
        }
        return chatroom;
    }



    public void addMessageToChatRoom(User user, Long chatId, String text, String time) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatId).orElse(null);
        if (chatRoom != null) {
            Message message = new Message(text, user, chatRoom, time);
            chatRoom.addMessage(message);
            chatRoomRepository.save(chatRoom);  // Save chatRoom with the new message
        }
    }


    public List<Message> getAllMessagesByChatRoom(Long id) {
        ChatRoom chatroom = chatRoomRepository.findById(id).orElse(null);
        if (chatroom != null) {
            return chatroom.getMessages();
        }
        return null;
    }

    public List<Message> getAllMessagesByChatRoom(String chatRoomName) {
        return chatRoomRepository.findByName(chatRoomName).getMessages();
    }

}
