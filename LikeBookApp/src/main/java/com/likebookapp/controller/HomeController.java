package com.likebookapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface HomeController {

    @GetMapping("/home")
    String home(Model model);



    @GetMapping("/")
    String index();
}
