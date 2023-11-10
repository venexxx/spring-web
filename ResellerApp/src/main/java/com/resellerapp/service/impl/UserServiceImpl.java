package com.resellerapp.service.impl;

import com.resellerapp.model.dto.UserRegisterBindingModel;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final   UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        User user = new User();

        user.setUsername(userRegisterBindingModel.getUsername());
        user.setPassword(userRegisterBindingModel.getPassword());
        user.setEmail(userRegisterBindingModel.getEmail());

       userRepository.save(user);

        return false;
    }
}
