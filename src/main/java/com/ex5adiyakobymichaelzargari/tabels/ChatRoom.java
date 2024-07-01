package com.ex5adiyakobymichaelzargari.tabels;

import com.ex5adiyakobymichaelzargari.AppConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ChatRoom Entity, represents a chat room in the application.
 */
@Getter
@Setter
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = AppConstants.CHAT_ERR_NAME_EMPTY)
    private String name;

    private String description;

    private boolean enabled = false;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Message> messages = new ArrayList<>();

    @ManyToMany(mappedBy = "chatRooms")
    private Set<User> users = new HashSet<>();



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

    public boolean isChatFull() {
        return users.size() > 50;
    }


}
