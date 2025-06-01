package edu.school21.models;

import lombok.*;
@Setter
@Getter

public class User {
    private Long id;
    private String login;
    private String password;
    private boolean status;

    public User(long id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
        status = false;
    }
}
