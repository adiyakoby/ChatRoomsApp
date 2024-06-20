package com.example.ex5adiyakobymichaelzargari.tabels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long id;


    @Setter
    @Getter
    @NotEmpty(message = "User Name is mandatory field")
    private String userName;

    @Getter
    @Setter
    @NotEmpty(message = "password is mandatory field")
    @Size(min = 4, message = "password must be at least 8 characters")
    private String password;

    @Getter
    @Setter
    @NotEmpty(message = "role is mandatory field")
    private String role;

    public User() {
    }

    public User(String name, String email, String password, String role) {
        this.userName = name;
        this.password = password;
        this.role = role;
    }

}
