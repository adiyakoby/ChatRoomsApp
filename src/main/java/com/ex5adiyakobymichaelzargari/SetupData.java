package com.ex5adiyakobymichaelzargari;


import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoomRepository;
import com.ex5adiyakobymichaelzargari.tabels.User;
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
        admin.setRole("ROLE_ADMIN");
        admin.addChatRoom(chatRoomRepository.findById(1L).orElse(null));

        User test = new User();
        test.setUsername("test");
        test.setPassword("test");
        test.setRole("ROLE_USER");
        test.addChatRoom(chatRoomRepository.findById(1L).orElse(null));


        userService.registerNewUser(admin);
        userService.registerNewUser(test);
    }

    /**
     * Initializes the database with chat room.
     */
    private void initChatRoom() {
        chatRoomService.createChatRoom("Home", "Home chat - for everyone.", true);
    }


}
