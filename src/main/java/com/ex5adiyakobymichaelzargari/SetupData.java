package com.ex5adiyakobymichaelzargari;


import com.ex5adiyakobymichaelzargari.Services.ChatRoomService;
import com.ex5adiyakobymichaelzargari.Services.MessageService;
import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.tabels.ChatRoom;
import com.ex5adiyakobymichaelzargari.tabels.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetupData {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MessageService messageService;

    @PostConstruct
    public void init() {
        initUsers();
        initChatRoom();
        initMessages();
    }

    private void initUsers() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole("ROLE_ADMIN");

        User test = new User();
        test.setUsername("test");
        test.setPassword("test");
        test.setRole("ROLE_USER");

        userService.registerNewUser(admin);
        userService.registerNewUser(test);
    }

    private void initChatRoom() {
        chatRoomService.createChatRoom("Home", "Home chat - for everyone.");
        System.out.println("Chat room 'Home' created successfully.");
    }

    private void initMessages() {
        messageService.registerNewMessage("Hello from system", "admin", "Home");
        messageService.registerNewMessage("Welcome to the chat room!", "admin", "Home");
        System.out.println("Messages registered successfully.");

    }
}
