package com.ex5adiyakobymichaelzargari.tabels;

import com.ex5adiyakobymichaelzargari.AppConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * User Entity, represents a user in the application.
 */
@Setter
@Getter
@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = AppConstants.USER_ERR_USERNAME_EMPTY)
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty(message = AppConstants.USER_ERR_PASSWORD_EMPTY)
    @Size(min = 6, message = AppConstants.USER_ERR_PASSWORD_LENGTH)
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
    private Set<ChatRoom> chatRooms = new TreeSet<>(Comparator.comparing(ChatRoom::getId));



    public User() {
        this.createdAt = new Date();
        this.role = AppConstants.ROLE_USER; // default is user
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
