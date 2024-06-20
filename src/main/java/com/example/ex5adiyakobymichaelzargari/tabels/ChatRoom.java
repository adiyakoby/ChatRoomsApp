package com.example.ex5adiyakobymichaelzargari.tabels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
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


    @Getter
    @Setter
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Message> messages = new ArrayList<>();
    public ChatRoom() {
    }

    public ChatRoom(String name, String description, User user) {
        this.name = name;
        this.description = description;
    }

    // Methods to manage the bi-directional relationship
    public void addMessage(Message message) {
        messages.add(message);
        message.setChatRoom(this);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
        message.setChatRoom(null);
    }


}
