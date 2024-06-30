package com.ex5adiyakobymichaelzargari.springSecurity;


import com.ex5adiyakobymichaelzargari.UserDataSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

/**
 * MyLogoutHandler handles the logout operation.
 */
@Component
public class MyLogoutHandler implements LogoutHandler{

    @Autowired
    private UserDataSession userDataSession;

    /**
     * Logs out the user, reset is session.
     *
     * @param httpServletRequest the HTTP request
     * @param httpServletResponse the HTTP response
     * @param authentication the authentication object
     */
    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,  Authentication authentication) {
        userDataSession.setUserLoggedIn(false);
        userDataSession.setUsername(null);
        userDataSession.setChatId(null);
    }
}
