package com.ex5adiyakobymichaelzargari.Principals;

import com.ex5adiyakobymichaelzargari.tabels.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * MyUserPrincipal implements UserDetails to represent the authenticated user in Spring Security.
 */
public class MyUserPrincipal implements UserDetails {
    private final User user;

    /**
     * Constructor to initialize MyUserPrincipal with the given user.
     *
     * @param user the user to be represented by this principal
     */
    public MyUserPrincipal(User user) {
        this.user = user;
    }

    /**
     * Returns the authorities granted to the user.
     *
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the user's password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used to authenticate the user.
     *
     * @return the user's username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Returns the user's ID.
     *
     * @return the user's ID
     */
    public Long getUserId() {
        return user.getId();
    }


}