package com.plannerapp.service.impl;

import com.plannerapp.model.dto.TaskAddDTO;
import com.plannerapp.model.dto.TaskViewDTO;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.enums.PriorityName;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    private final PriorityRepository priorityRepository;


    public TaskServiceImpl(TaskRepository taskRepository, PriorityRepository priorityRepository) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void addTask(TaskAddDTO taskAddDTO) {
        Task task = new Task();
        Priority priority = priorityRepository.findByPriorityName(PriorityName.valueOf(taskAddDTO.getPriority()));

        task.setDescription(taskAddDTO.getDescription());
        task.setPriority(priority);
        task.setDueDate(taskAddDTO.getDueDate());


        taskRepository.save(task);


    }

    @Override
    public Set<TaskViewDTO> mapTaskToView(Set<Task> tasks) {
        Set<TaskViewDTO> taskViewDTOS = new HashSet<>();
        tasks.stream().forEach(task -> {
            TaskViewDTO taskView = new TaskViewDTO();
            taskView.setDescription(task.getDescription());
            taskView.setPriority(task.getPriority().getPriorityName().toString());
            taskView.setDueDate(task.getDueDate());
            taskView.setId(task.getId());

            taskViewDTOS.add(taskView);
        });
        return taskViewDTOS;
    }
}
