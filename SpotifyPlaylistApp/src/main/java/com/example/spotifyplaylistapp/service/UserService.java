package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.RegisterDTO;

public interface UserService {

    void register(RegisterDTO registerDTO);

    boolean checkCredentials(String username, String password);

    void login(String username);

    void logout();
}
