package com.ex5adiyakobymichaelzargari;


import com.ex5adiyakobymichaelzargari.Services.UserService;
import com.ex5adiyakobymichaelzargari.tabels.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetupData {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        initUsers();
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
}
