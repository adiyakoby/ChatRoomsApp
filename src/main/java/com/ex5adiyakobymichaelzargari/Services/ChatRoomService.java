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

    public ChatRoom createChatRoom(String name, String description) {
        if (chatRoomRepository.findByName(name) != null) {
            return null;
        }
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        chatRoom.setDescription(description);
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom getChatRoomById(Long id) {
        return chatRoomRepository.findById(id).orElse(null);
    }

    public void addMessageToChatRoom(User user, String chatRoomName, String text, String time) {
        ChatRoom chatRoom = chatRoomRepository.findByName(chatRoomName);
        if (chatRoom != null) {
            Message message = new Message(text, user, chatRoom, time);
            chatRoom.addMessage(message);
            chatRoomRepository.save(chatRoom);  // Save chatRoom with the new message
        }
    }

//    public Message addMessageToChatRoom(User user, String chatRoomName, String content, String time) { // user, "Home", message.getText()
//        ChatRoom chatRoom = chatRoomRepository.findByName(chatRoomName); // need to add CHECK if not FOUND
//
//        Message message = new Message(content, user, chatRoom, time);
//        chatRoom.addMessage(message);
////        System.out.println(chatRoomRepository.findMessagesById(1L));
//        messageRepository.save(message);
//
//        return message;
//    }

    @Transactional(readOnly = true)
    public List<Message> getAllMessagesByChatRoom(Long chatRoomId) {
        return chatRoomRepository.findMessagesById(chatRoomId);
    }

}
