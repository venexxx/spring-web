package com.resellerapp.repository;

import com.resellerapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User,UUID> {


    User findByUsernameAndPassword(String username,String password);
}
