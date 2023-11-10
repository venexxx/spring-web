package com.likebookapp.controller;

import com.likebookapp.model.dtos.PostDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface PostController {


    @GetMapping("/add-post")
    public String addPost();

    @PostMapping("/add-post")
    public String addPost(PostDTO postDTO,BindingResult result, RedirectAttributes redirectAttributes);
}
