package com.plannerapp.service.impl;


import com.plannerapp.model.dto.RegisterDTO;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.UserService;
import com.plannerapp.util.LoggedUser;
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
        User user = userRepository.findByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public void logout() {
        loggedUser.logout();
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return false;
        }

        return passwordEncoder.matches(password, user.getPassword());
    }
}
