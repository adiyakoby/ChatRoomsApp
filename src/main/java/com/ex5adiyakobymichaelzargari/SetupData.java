package com.ex5adiyakobymichaelzargari;


import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import com.ex5adiyakobymichaelzargari.tabels.User;
import com.ex5adiyakobymichaelzargari.tabels.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SetupData initializes the database with some initial data.
 */
@Component
public class SetupData {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatRoomService chatRoomService;


    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Initializes the database with users .
     */
    @PostConstruct
    public void init() {
        initChatRoom();
        initUsers();
    }

    private void initUsers() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(AppConstants.ROLE_ADMIN);
        admin.addChatRoom(chatRoomRepository.findById(1L).orElse(null));

        try {
            userService.registerNewUser(admin);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initializes the database with chat room.
     */
    private void initChatRoom() {
        try {
            chatRoomService.createChatRoom("Home", "Home chat - for everyone.", true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
