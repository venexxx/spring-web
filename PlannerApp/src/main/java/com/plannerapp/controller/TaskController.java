package com.plannerapp.controller;

import com.plannerapp.model.dto.TaskAddDTO;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class TaskController {
    private final LoggedUser loggedUser;
    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskController(LoggedUser loggedUser, TaskService taskService, TaskRepository taskRepository, UserRepository userRepository) {
        this.loggedUser = loggedUser;
        this.taskService = taskService;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/task-add")
    public ModelAndView taskAdd(ModelAndView modelAndView){
        modelAndView.setViewName("task-add");
        if (!loggedUser.isLogged()){
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }


    @PostMapping("/task-add")
    public ModelAndView taskAdd(@Valid TaskAddDTO taskAddDTO, BindingResult result, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/home");

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("taskAddDTO", taskAddDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.taskAddDTO", result);

            modelAndView.setViewName("redirect:/task-add");
            return modelAndView;
        }

        taskService.addTask(taskAddDTO);

        return modelAndView;
    }

    @GetMapping("/task/assing-to-me/{id}")
    public ModelAndView assignPost(@PathVariable Long id){
        User user = userRepository.findByUsername(loggedUser.getUsername());
        Task task = taskRepository.findById(id).orElse(null);
        taskRepository.delete(task);
        task.setUser(user);
        taskRepository.save(task);

        return new ModelAndView("redirect:/home");
    }
    @GetMapping("/task/return/{id}")
    public ModelAndView removeTask(@PathVariable Long id){
        Task task = taskRepository.findById(id).orElse(null);
        taskRepository.delete(task);
        task.setUser(null);
        taskRepository.save(task);

        return new ModelAndView("redirect:/home");
    }
    @GetMapping("/task/done-and-remove/{id}")
    public ModelAndView done(@PathVariable Long id){
        Task task = taskRepository.findById(id).orElse(null);
        taskRepository.delete(task);

        return new ModelAndView("redirect:/home");
    }

    @ModelAttribute
    public TaskAddDTO taskAddDTO() {
        return new TaskAddDTO();
    }
}
