package com.plannerapp.service;

import com.plannerapp.model.dto.RegisterDTO;
import com.plannerapp.model.dto.TaskAddDTO;
import com.plannerapp.model.dto.TaskViewDTO;
import com.plannerapp.model.entity.Task;

import java.util.Set;

public interface TaskService {

    void addTask(TaskAddDTO taskAddDTO);

    Set<TaskViewDTO> mapTaskToView(Set<Task> tasks);
}
