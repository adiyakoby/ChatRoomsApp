package com.ex5adiyakobymichaelzargari.tabels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Setter
@Getter
@Entity
public class User  {
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

    public User() {
        this.createdAt = new Date();
        this.role = "ROLE_USER"; // default is user
    }

}
