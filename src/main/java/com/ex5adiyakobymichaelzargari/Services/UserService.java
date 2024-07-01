package com.ex5adiyakobymichaelzargari.Services;


import com.ex5adiyakobymichaelzargari.tabels.ChatRoom;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import com.ex5adiyakobymichaelzargari.tabels.User;
import com.ex5adiyakobymichaelzargari.tabels.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * UserService handles user related actions such as registering, banning, and unbanning users.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user if the username is not already taken.
     *
     * @param user the user to be registered
     * @return the registered user or null if the username is already taken
     */
    public void registerNewUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new EntityExistsException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addChatRoom(chatRoomRepository.findById(1L).orElse(null));
        userRepository.save(user);
    }

    /**
     * Adds a chat room to a user.
     *
     * @param userId the ID of the user
     * @param chatRoomId the ID of the chat room
     */
    public void addChatRoomToUser(Long userId, Long chatRoomId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new RuntimeException("Chat Room not found"));
        user.addChatRoom(chatRoom);
        userRepository.save(user);
    }

    /**
     * Removes a chat room from a user.
     *
     * @param userId the ID of the user
     * @param chatRoomId the ID of the chat room
     */
    public void removeChatRoomFromUser(Long userId, Long chatRoomId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new RuntimeException("Chat Room not found"));
        user.deleteChatRoom(chatRoom);
        userRepository.save(user);
    }

    /**
     * Retrieves the chat rooms of a user.
     *
     * @param userId the ID of the user
     * @return the chat rooms of the user
     */
    public Set<ChatRoom> getUserChatRooms(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getChatRooms();
    }

    /**
     * Retrieves all users.
     *
     * @return the list of all users
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Bans a user by setting their role to ROLE_BANNED.
     *
     * @param id the ID of the user
     */
    public void banUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        user.setRole("ROLE_BANNED");
        userRepository.save(user);
    }

    /**
     * Unbans a user by setting their role to ROLE_USER.
     *
     * @param id the ID of the user
     */
    public void unbanUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

}
