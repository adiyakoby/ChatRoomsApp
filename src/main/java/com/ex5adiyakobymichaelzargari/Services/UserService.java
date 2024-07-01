package com.ex5adiyakobymichaelzargari.Services;


import com.ex5adiyakobymichaelzargari.AppConstants;
import com.ex5adiyakobymichaelzargari.Principals.MyUserPrincipal;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoom;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import com.ex5adiyakobymichaelzargari.tabels.User;
import com.ex5adiyakobymichaelzargari.tabels.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * Registers a new user if the username is not already taken.
     *
     * @param user the user to be registered
     * @return the registered user or null if the username is already taken
     */
    public void registerNewUser(User user) {
        if (userRepository.findByUsername(user.getUsername().toLowerCase()) != null) {
            throw new EntityExistsException(AppConstants.USER_SRV_ERR_USERNAME_TAKEN);
        }
        user.setUsername(user.getUsername().toLowerCase());
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
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException(AppConstants.USER_SRV_ERR_NOT_FOUND));
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(
                () -> new RuntimeException(AppConstants.CHAT_SRV_ERR_NOT_FOUND));
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
        if (chatRoomId == 1L) {
            throw new RuntimeException(AppConstants.USER_SRV_ERR_CANT_RMV);
        }
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException(AppConstants.USER_SRV_ERR_NOT_FOUND));
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(
                () -> new RuntimeException(AppConstants.CHAT_SRV_ERR_NOT_FOUND));
        user.deleteChatRoom(chatRoom);
        userRepository.save(user);
    }

    /**
     * Retrieves the chat rooms of a user.
     *
     * @param userId the ID of the user
     * @return the chat rooms of the user
     */
    public List<ChatRoom> getUserChatRooms(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException(AppConstants.USER_SRV_ERR_NOT_FOUND));
        return user.getChatRooms().stream()
                .sorted(Comparator.comparing(ChatRoom::getId)) // Sort by ID
                .collect(Collectors.toList());
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
     * and deletes all of the user's sessions the spring security is management
     * for this user.
     * @param id the ID of the user
     */
    public void banUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(AppConstants.USER_SRV_ERR_INVALID_ID));
        user.setRole(AppConstants.ROLE_BANNED);
        userRepository.save(user);

        // Invalidate user sessions
        List<SessionInformation> sessions = sessionRegistry.getAllSessions(new MyUserPrincipal(user), false);
        for (SessionInformation session : sessions) {
            session.expireNow();
        }

    }

    /**
     * Unbans a user by setting their role to ROLE_USER.
     *
     * @param id the ID of the user
     */
    public void unbanUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(AppConstants.USER_SRV_ERR_INVALID_ID));
        user.setRole(AppConstants.ROLE_USER);
        userRepository.save(user);
    }

}
