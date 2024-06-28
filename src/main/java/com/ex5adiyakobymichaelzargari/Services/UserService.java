package com.ex5adiyakobymichaelzargari.Services;


import com.ex5adiyakobymichaelzargari.tabels.ChatRoom;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import com.ex5adiyakobymichaelzargari.tabels.User;
import com.ex5adiyakobymichaelzargari.tabels.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


/** Service layer is used to implement business logic
 *  it is a good practice to separate the business logic from the controller
 *  and the repository
 *  this way the controller is only responsible for handling the request and response
 *  the repository is only responsible for the database operations
 *  and the service is responsible for the business logic
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addChatRoom(chatRoomRepository.findById(1L).orElse(null));
        return userRepository.save(user);
    }

    public void addChatRoomToUser(Long userId, Long chatRoomId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new RuntimeException("Chat Room not found"));
        user.addChatRoom(chatRoom);
        userRepository.save(user);
    }

    public void removeChatRoomFromUser(Long userId, Long chatRoomId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new RuntimeException("Chat Room not found"));
        user.deleteChatRoom(chatRoom);
        userRepository.save(user);
    }

    public Set<ChatRoom> getUserChatRooms(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getChatRooms();
    }

}
