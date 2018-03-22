package com.team.mrobot.web.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Project: TaaS
 * Author: AndrewLiang
 * Date: 2017/9/23
 * Description: User or Admin For Web
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//ID

    @NotEmpty(message = "Name cannot be empty!")
    @Size(min = 2, max = 28)
    @Column(nullable = false, length = 28)
    private String name;//Name of user or admin

    @NotEmpty(message = "Username cannot be empty!")
    @Size(min = 3, max = 20)
    @Column(nullable = false, length = 20, unique = true)
    private String username;//Login name of user or admin

    @NotEmpty(message = "Email cannot be empty!")
    @Size(max = 50)
    @Email(message = "Email is out of format")
    @Column(nullable = false, length = 50, unique = true)
    private String email;//Email

    @NotEmpty(message = "Password cannot be empty!")
    @Size(min = 6, max = 16)
    @Column(nullable = false, length = 16)
    private String password;//Login password of user or admin

    protected User() {
    }

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
