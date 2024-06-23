package com.ex5adiyakobymichaelzargari.tabels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "User Name is mandatory field")
    private String name;

    @NotEmpty(message = "password is mandatory field")
    @Size(min = 4, message = "password must be at least 8 characters")
    private String password;

    @NotEmpty(message = "role is mandatory field")
    private String role;

    @Getter
    @Setter
    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt;

    public User() {
    }

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.createdAt = new java.util.Date();
    }

}
