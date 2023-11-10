package com.resellerapp.service;

import com.resellerapp.model.dto.UserRegisterBindingModel;

public interface UserService {


    boolean register(UserRegisterBindingModel userRegisterBindingModel);
}
