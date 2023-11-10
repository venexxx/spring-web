package com.resellerapp.controller;

import com.resellerapp.model.dto.UserLoginBindingModel;
import com.resellerapp.model.dto.UserRegisterBindingModel;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.UserService;
import com.resellerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    public UserController(UserService userService, UserRepository userRepository, LoggedUser loggedUser) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }


    @GetMapping("users/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("users/register")
    public String register(){
        return "register.html";
    }


    @PostMapping("users/register")
    public String register(UserRegisterBindingModel userRegisterBindingModel){

        if (userService.register(userRegisterBindingModel)){
            return "redirect:/";
        }else {
            return "redirect:/users/login";
        }

    }

    @PostMapping("users/login")
    public String login(UserLoginBindingModel userLoginBindingModel){

        User user = userRepository.findByUsernameAndPassword(userLoginBindingModel.getUsername(),userLoginBindingModel.getPassword());

        if (user != null){
            loggedUser.setLogged(true);
            return "home.html";
        }else {
            return "redirect:/users/login";

        }

    }
}
