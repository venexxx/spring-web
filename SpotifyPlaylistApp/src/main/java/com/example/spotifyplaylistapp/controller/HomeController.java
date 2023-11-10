package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final SongRepository songRepository;

    private final UserRepository userRepository;

    public HomeController(LoggedUser loggedUser, SongRepository songRepository, UserRepository userRepository) {
        this.loggedUser = loggedUser;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/home")
    public ModelAndView home(Model model){
        ModelAndView modelAndView = new ModelAndView("home");
        if (!loggedUser.isLogged()){
            modelAndView.setViewName("redirect:/");
        }
        User user = userRepository.findUserByUsername(loggedUser.getUsername());
        Set<Song> allByUserId = this.songRepository.findAllByUserId(loggedUser.getId());
        List<Song> allSongs = this.songRepository.findAll();


        model.addAttribute("songs", allSongs);
        model.addAttribute("playlist", allByUserId);
        return modelAndView;
    }
}
