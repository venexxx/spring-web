package com.plannerapp.controller;

import com.plannerapp.model.dto.TaskViewDTO;
import com.plannerapp.model.entity.Task;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;

    private final TaskService taskService;

    private final TaskRepository taskRepository;

    public HomeController(LoggedUser loggedUser, TaskService taskService, TaskRepository taskRepository) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;

        this.taskRepository = taskRepository;
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView){
//        modelAndView.setViewName("home");
//        if (!loggedUser.isLogged()){
//            modelAndView.setViewName("redirect:/");
//        }
//
//        return modelAndView;

        modelAndView = new ModelAndView("home");
        if (!loggedUser.isLogged()){
            modelAndView.setViewName("redirect:/");
        }
        Set<TaskViewDTO> allByUserId =taskService.mapTaskToView(this.taskRepository.findAllByUserId(loggedUser.getId()));
        Set<TaskViewDTO> allAvailableTasks =taskService.mapTaskToView(this.taskRepository.findAllByUserIdIsNotIn());


        modelAndView.addObject("allAvailable", allAvailableTasks);
        modelAndView.addObject("tasks", allByUserId);
        return modelAndView;
    }

    @GetMapping()
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        if (loggedUser.isLogged()){
            modelAndView.setViewName("redirect:/home");
        }

        return modelAndView;
    }
}
