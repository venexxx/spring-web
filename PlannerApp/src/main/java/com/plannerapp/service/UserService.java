package com.plannerapp.service;


import com.plannerapp.model.dto.RegisterDTO;

public interface UserService {

    void register(RegisterDTO registerDTO);

    boolean checkCredentials(String username, String password);

    void login(String username);

    void logout();
}
