package com.example.spotifyplaylistapp.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterDTO {

    @NotBlank(message = "Username length must be between 2 and 20 characters!")
    @Size(min = 3, max = 30,message = "")
    private String username;

    @NotBlank(message = "Password length must be between 2 and 20 characters!")
    @Size(min = 2, max = 30,message = "")
    private String password;


    @NotBlank(message = "")
    private String confirmPassword;

    @NotBlank(message = "Email cannot be empty!")
    @Email
    private String email;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
