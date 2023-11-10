package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.LoginDTO;
import com.example.spotifyplaylistapp.model.dto.RegisterDTO;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final LoggedUser loggedUser;
    private final UserService userService;

    public UserController(LoggedUser loggedUser, UserService userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        if (loggedUser.isLogged()){
           modelAndView.setViewName("redirect:/home");
        }
        return modelAndView;
    }
    @PostMapping("/login")
    public String loginConfirm(@Valid LoginDTO loginDTO, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", result);

            return "redirect:/login";
        }

        boolean validCredentials = this.userService.checkCredentials(loginDTO.getUsername(), loginDTO.getPassword());

        if (!validCredentials) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("validCredentials", false);
            return "redirect:/login";
        }

        this.userService.login(loginDTO.getUsername());
        return "redirect:/home";
    }

    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView("register");
        if (loggedUser.isLogged()){
            modelAndView.setViewName("redirect:/home");
        }
        return modelAndView;
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            result.addError(
                    new FieldError(
                            "differentConfirmPassword",
                            "confirmPassword",
                            "Passwords must be the same."));
            result.addError(
                    new FieldError(
                            "differentConfirmPassword",
                            "password",
                            "Passwords must be the same."));
        }

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("registerDTO", registerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", result);

            return "redirect:/register";
        }

        this.userService.register(registerDTO);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    String logout(){
        if (!this.loggedUser.isLogged()) {
            return "redirect:/login";
        }

        this.userService.logout();
        return "redirect:/";
    }

    @ModelAttribute
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }

    @ModelAttribute
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("validCredentials");
    }

}
