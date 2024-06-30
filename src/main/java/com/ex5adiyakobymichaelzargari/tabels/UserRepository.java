package com.ex5adiyakobymichaelzargari.tabels;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * UserRepository provides CRUD operations for users.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
