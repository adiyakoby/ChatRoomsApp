package com.example.ex5adiyakobymichaelzargari.tabels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;

@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long id;

    @Getter
    @Setter
    @NotEmpty(message = "ChatRoom name is mandatory field")
    private String name;

    @Getter
    @Setter
    private String description;


    //private ArrayList<Message> messages = new ArrayList<>();

    public ChatRoom() {
    }

    public ChatRoom(String name, String description, User user) {
        this.name = name;
        this.description = description;
        //this.user = user;
    }


}
