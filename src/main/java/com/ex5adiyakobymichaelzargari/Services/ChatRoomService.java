package com.ex5adiyakobymichaelzargari.Services;

import com.ex5adiyakobymichaelzargari.tabels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;

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

    public List<ChatRoom> findPendingChatRooms() {
        return chatRoomRepository.findByEnabledFalse();
    }

    public List<ChatRoom> findEnabledChatRooms() {
        return chatRoomRepository.findByEnabledTrue();
    }

    public void approveChatRoom(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid chat room ID"));
        chatRoom.setEnabled(true);
        chatRoomRepository.save(chatRoom);
    }

    public void disapproveChatRoom(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid chat room ID"));
        chatRoomRepository.delete(chatRoom);
    }

    public void deleteChatRoom(Long id) {
        chatRoomRepository.deleteById(id);
    }

    public void enableChatRoom(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid chat room ID"));
        chatRoom.setEnabled(true);
        chatRoomRepository.save(chatRoom);
    }

    public void disableChatRoom(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid chat room ID"));
        chatRoom.setEnabled(false);

        Set<User> users = chatRoom.getUsers();
        users.forEach(user -> userService.removeChatRoomFromUser(user.getId(), id));

        chatRoomRepository.save(chatRoom);
    }

}
