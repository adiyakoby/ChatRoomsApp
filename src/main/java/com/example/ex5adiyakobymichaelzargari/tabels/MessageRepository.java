package com.example.ex5adiyakobymichaelzargari.tabels;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> getMessages();
    Message getMessage(int id);

}
