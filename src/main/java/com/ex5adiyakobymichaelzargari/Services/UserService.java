package com.ex5adiyakobymichaelzargari.Services;


import com.ex5adiyakobymichaelzargari.tabels.User;
import com.ex5adiyakobymichaelzargari.tabels.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


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
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return null;
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

}
