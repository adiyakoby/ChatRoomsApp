package com.ex5adiyakobymichaelzargari.tabels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(User.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "User Name is mandatory field")
    private String username;

    @NotEmpty(message = "password is mandatory field")
    @Size(min = 4, message = "password must be at least 4 characters")
    private String password;


    @Column(nullable = false)
    private boolean enabled;

    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities = new HashSet<>();


    public User() {
        this.enabled = true;
        this.createdAt = new Date();
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.createdAt = new Date();
    }


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + username;
    }

}
