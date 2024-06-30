package com.ex5adiyakobymichaelzargari.Services;

import com.ex5adiyakobymichaelzargari.Principals.MyUserPrincipal;
import com.ex5adiyakobymichaelzargari.tabels.User;
import com.ex5adiyakobymichaelzargari.tabels.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * MyUserDetailsService implements UserDetailsService to load user-specific data.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads a user by their username.
     *
     * @param username the username of the user
     * @return the user details
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }

}