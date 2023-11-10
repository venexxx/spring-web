package com.likebookapp.controller;

import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeControllerImpl implements HomeController{
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public HomeControllerImpl(LoggedUser loggedUser, UserRepository userRepository, PostRepository postRepository) {
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }



    @Override
    public String home(Model model) {

        if (!loggedUser.isLogged()){
            return "redirect:/";
        }

        User user = userRepository.getUserByUsername(loggedUser.getUsername());


        List<Post> postsFromCurrentUser = postRepository.findAllByUserId(user.getId());
        model.addAttribute("userPosts", postsFromCurrentUser);
       List<Post> postsFromOtherUsers = postRepository.getPostsByIdIsNot(user.getId());
        model.addAttribute("otherUserPosts", postsFromOtherUsers);
        model.addAttribute("user", user);

        return "home";
    }

    @Override
    public String index() {
        if (loggedUser.isLogged()){
            return "redirect:/home";
        }
        return "index";
    }
}
