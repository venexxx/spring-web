package com.example.spotifyplaylistapp.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDTO {


    @NotBlank(message = "Username length must be between 2 and 20 characters!")
    @Size(min = 3, max = 30,message = "")
    private String username;

    @NotBlank(message = "Password length must be between 2 and 20 characters!")
    @Size(min = 2, max = 30,message = "")
    private String password;

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
}
