package com.ex5adiyakobymichaelzargari.tabels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "username cannot be empty")
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "password cannot be empty")
    private String password;

    @NotNull
    private String role;

    @NotNull
    private Date createdAt;


    @ManyToMany
    @JoinTable(
            name = "user_chatroom",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chatroom_id")
    )
    private Set<ChatRoom> chatRooms = new TreeSet<>(Comparator.comparing(ChatRoom::getId,Long::compareTo));



    public User() {
        this.createdAt = new Date();
        this.role = "ROLE_USER"; // default is user
    }

    public void addChatRoom(ChatRoom chatroom) {
        if (chatroom != null) {
            chatRooms.add(chatroom);
        }
    }

    public void deleteChatRoom(ChatRoom chatroom) {
        if (chatroom != null) {
            chatRooms.remove(chatroom);
        }
    }


}
