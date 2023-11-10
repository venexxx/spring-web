package com.likebookapp.service;

import com.likebookapp.model.dtos.RegisterDTO;
import com.likebookapp.model.entity.User;

public interface UserService {



     void register(RegisterDTO registerDTO);
     User mapUser(RegisterDTO registerDTO);
     boolean checkCredentials (String username, String password);
     void login(String username);
     void logout();
}
