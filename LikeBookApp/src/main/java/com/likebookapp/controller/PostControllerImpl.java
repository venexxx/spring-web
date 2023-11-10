package com.likebookapp.controller;

import com.likebookapp.model.dtos.LoginDTO;
import com.likebookapp.model.dtos.PostDTO;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.service.PostService;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PostControllerImpl implements PostController {
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final PostService service;

    public PostControllerImpl(LoggedUser loggedUser, UserRepository userRepository, PostService service) {
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.service = service;
    }

    @Override
    public String addPost() {
        if (!loggedUser.isLogged()){
            return "redirect:/";
        }
        return "post-add";
    }

    @Override
    public String addPost(@Valid PostDTO postDTO, BindingResult result, RedirectAttributes redirectAttributes) {




        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("postDTO", postDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.postDTO", result);
            return "redirect:/add-post";
        }

        service.addPost(postDTO);
        return "home";
    }

    @ModelAttribute
    public PostDTO postDTO() {
        return new PostDTO();
    }
}
