package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.dto.RegisterDTO;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userRepository.save(user);

    }

    @Override
    public void login(String username) {
        User user = userRepository.findUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public void logout() {
        loggedUser.logout();
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            return false;
        }

        return passwordEncoder.matches(password, user.getPassword());
    }
}
