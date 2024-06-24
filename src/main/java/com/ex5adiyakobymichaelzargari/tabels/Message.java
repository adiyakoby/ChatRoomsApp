package com.ex5adiyakobymichaelzargari.tabels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotEmpty(message = "message is mandatory field")
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @Setter
    @Column(name = "created_at", updatable = false)
    private java.util.Date createdAt;

    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;




    public Message(){
        this.createdAt = new Date();
    }

    public Message(String message, User user, ChatRoom chatRoom) {
        this.message = message;
        this.user = user;
        this.chatRoom = chatRoom;
        this.createdAt = new Date();
    }
}
