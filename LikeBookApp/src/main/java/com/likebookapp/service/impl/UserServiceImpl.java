package com.likebookapp.service.impl;

import com.likebookapp.model.dtos.RegisterDTO;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.service.UserService;
import com.likebookapp.util.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserRepository repository;

    private final LoggedUser loggedUser;

    public UserServiceImpl(PasswordEncoder encoder, UserRepository repository, LoggedUser loggedUser) {
        this.encoder = encoder;
        this.repository = repository;
        this.loggedUser = loggedUser;

    }

    @Override
    public void register(RegisterDTO registerDTO) {
        this.repository.save(this.mapUser(registerDTO));
    }

    @Override
    public User mapUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        return user;
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        User user = repository.getUserByUsername(username);

        if (user == null) {
            return false;
        }

        return encoder.matches(password, user.getPassword());
    }

    @Override
    public void login(String username) {
        User user = repository.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public void logout() {
        this.loggedUser.reset();
    }
}
