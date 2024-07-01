package com.ex5adiyakobymichaelzargari.Services;

import com.ex5adiyakobymichaelzargari.tabels.*;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * ChatRoomService handles chat room related actions such as creating, adding, and deleting chat rooms.
 */
@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;


    @Autowired
    private UserService userService;

    /**
     *
     * @param name the name of the chat room
     */
    private void handleDuplicateChatRoom(String name) {
        if (chatRoomRepository.findByName(name) != null) {
            throw new EntityExistsException("Chat room name already exists");
        }
    }

    /**
     * Creates a new chat room if a room with the same name does not exist.
     *
     * @param name the name of the chat room
     * @param description the description of the chat room
     * @param isEnabled the initial status of the chat room
     */
    public void createChatRoom(String name, String description, boolean isEnabled) {

        handleDuplicateChatRoom(name);

        ChatRoom chatroom = new ChatRoom();
        chatroom.setName(name);
        chatroom.setDescription(description);
        chatroom.setEnabled(isEnabled);
        chatRoomRepository.save(chatroom);
    }

    /**
     * Creates a new chat room if a room with the same name does not exist.
     *
     * @param chatroom chatroom to save.
     */
    public void createChatRoom(ChatRoom chatroom) {
        handleDuplicateChatRoom(chatroom.getName());

        chatRoomRepository.save(chatroom);
    }

    /**
     * Retrieves an enabled chat room by its name.
     *
     * @param chatRoomName the name of the chat room
     * @return the chat room if it exists and is enabled, otherwise null
     */
    public ChatRoom getChatRoomByName(String chatRoomName) {
        ChatRoom chatroom = chatRoomRepository.findByName(chatRoomName);
        if (chatroom == null || !chatroom.isEnabled()) {
            return null;
        }
        return chatroom;
    }

    /**
     * Adds a message to a chat room.
     *
     * @param user the user who sends the message
     * @param chatId the ID of the chat room
     * @param text the message text
     * @param time the time the message was sent
     */
    public void addMessageToChatRoom(User user, Long chatId, String text, String time) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatId).orElse(null);
        if (chatRoom != null) {
            Message message = new Message(text, user, chatRoom, time);
            chatRoom.addMessage(message);
            chatRoomRepository.save(chatRoom);  // Save chatRoom with the new message
        }
    }

    /**
     * Retrieves all messages by chat room ID.
     *
     * @param id the ID of the chat room
     * @return the list of messages or null if the chat room does not exist
     */
    public List<Message> getAllMessagesByChatRoom(Long id) {
        ChatRoom chatroom = chatRoomRepository.findById(id).orElse(null);
        if (chatroom != null) {
            return chatroom.getMessages();
        }
        return null;
    }

    /**
     * Retrieves all pending chat rooms.
     *
     * @return the list of pending chat rooms
     */
    public List<ChatRoom> findPendingChatRooms() {
        return chatRoomRepository.findByEnabledFalse();
    }

    /**
     * Retrieves all enabled chat rooms.
     *
     * @return the list of enabled chat rooms
     */
    public List<ChatRoom> findEnabledChatRooms() {
        return chatRoomRepository.findByEnabledTrue();
    }

    /**
     * Approves a chat room by setting it to enabled.
     *
     * @param id the ID of the chat room
     */
    public void approveChatRoom(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid chat room ID"));
        chatRoom.setEnabled(true);
        chatRoomRepository.save(chatRoom);
    }

    /**
     * Disapproves a chat room by deleting it.
     *
     * @param id the ID of the chat room
     */
    public void disapproveChatRoom(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid chat room ID"));
        chatRoomRepository.delete(chatRoom);
    }

    /**
     * Deletes a chat room by its ID.
     *
     * @param id the ID of the chat room
     */
    public void deleteChatRoom(Long id) {
        chatRoomRepository.deleteById(id);
    }

    /**
     * Enables a chat room.
     *
     * @param id the ID of the chat room
     */
    public void enableChatRoom(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid chat room ID"));
        chatRoom.setEnabled(true);
        chatRoomRepository.save(chatRoom);
    }

    /**
     * Disables a chat room.
     *
     * @param id the ID of the chat room
     */
    public void disableChatRoom(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid chat room ID"));
        chatRoom.setEnabled(false);

        Set<User> users = chatRoom.getUsers();
        users.forEach(user -> userService.removeChatRoomFromUser(user.getId(), id));

        chatRoomRepository.save(chatRoom);
    }

}
