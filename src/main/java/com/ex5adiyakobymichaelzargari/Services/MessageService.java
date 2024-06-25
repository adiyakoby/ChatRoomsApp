package com.ex5adiyakobymichaelzargari.Services;

import com.ex5adiyakobymichaelzargari.tabels.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;


    public void registerNewMessage(String msg, String userName, String chatroomName) throws EntityNotFoundException {
        ChatRoom chatRoom = chatRoomRepository.findByName(chatroomName);
        User user = userRepository.findByUsername(userName);
        if (chatRoom == null) {
            throw new EntityNotFoundException("Chat Room Not Found");
        } else if (user == null) {
            throw new EntityNotFoundException("User Not Found");
        }
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        Message message = new Message(msg, user, chatRoom, time);
        messageRepository.save(message);
    }


    public List<Message> getAllMessages(String chatRoomName) {
        ChatRoom chatRoom = chatRoomRepository.findByName(chatRoomName);
        return chatRoom.getMessages();
    }
}
