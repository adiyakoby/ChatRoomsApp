package com.example.ex5adiyakobymichaelzargari.tabels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long id;

    @Getter
    @Setter
    @NotEmpty(message = "message is mandatory field")
    private String message;

    @ManyToOne
    private User user;

    public Message(){}

    public Message(String message, User user) {
        this.message = message;
        this.user = user;
    }
}
