package com.likebookapp.controller;

import com.likebookapp.model.dtos.LoginDTO;
import com.likebookapp.model.dtos.RegisterDTO;
import com.likebookapp.service.UserService;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserControllerImpl implements UserController{
    private final LoggedUser loggedUser;
    private final UserService userService;

    public UserControllerImpl(LoggedUser loggedUser, UserService userService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    @Override
    public String login(Model model) {
        if (this.loggedUser.isLogged()){
            return "redirect:/home";
        }
        return "login";
    }

    @Override
    public String loginConfirm(LoginDTO loginDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        boolean validCredentials = this.userService.checkCredentials(loginDTO.getUsername(), loginDTO.getPassword());
        if (!validCredentials) {
            result.addError(
                    new FieldError(
                            "incorrectPassword",
                            "password",
                            "Incorrect password."));
        }

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("loginDTO", loginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", result);

            return "redirect:/users/login";
        }
        this.userService.login(loginDTO.getUsername());
        return "redirect:/home";
    }

    @Override
    public String register() {
        if (this.loggedUser.isLogged()){
            return "redirect:/home";
        }
        return "register";
    }
//
    @Override
    public String registerConfirm(RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            result.addError(
                    new FieldError(
                            "differentConfirmPassword",
                            "confirmPassword",
                            "Passwords must be the same."));
        }

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("registerDTO", registerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", result);

            return "redirect:/users/register";
        }

        userService.register(registerDTO);
        return "redirect:/users/login";

    }
//
    @Override
    public String logout() {
        if (!loggedUser.isLogged()){
            return "redirect:/";
        }
        loggedUser.reset();
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
}
