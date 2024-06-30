package com.ex5adiyakobymichaelzargari;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@Component
@SessionScope
public class UserDateSession {
    private String username;
    private boolean isUserLoggedIn;
    private Long chatId = 1L;

    public UserDateSession() {
    }

    public UserDateSession(String username, boolean isUserLoggedIn, Long chatId) {
        this.username = username;
        this.isUserLoggedIn = isUserLoggedIn;
        this.chatId = chatId;
    }

}
