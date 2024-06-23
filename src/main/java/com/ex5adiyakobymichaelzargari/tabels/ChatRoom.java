package com.ex5adiyakobymichaelzargari.tabels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotEmpty(message = "ChatRoom name is mandatory field")
    private String name;

    private String description;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Message> messages = new ArrayList<>();
    public ChatRoom() {
    }

    public ChatRoom(String name, String description, User user) {
        this.name = name;
        this.description = description;
    }

    public void addMessage(Message message) {
        messages.add(message);
        message.setChatRoom(this);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
        message.setChatRoom(null);
    }


}
