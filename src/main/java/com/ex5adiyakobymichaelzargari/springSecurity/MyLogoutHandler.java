package com.ex5adiyakobymichaelzargari.springSecurity;


import com.ex5adiyakobymichaelzargari.UserDateSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class MyLogoutHandler implements LogoutHandler{

    @Autowired
    private UserDateSession userDateSession;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,  Authentication authentication) {
        userDateSession.setUserLoggedIn(false);
        userDateSession.setUsername(null);
        userDateSession.setChatId(null);
    }
}
