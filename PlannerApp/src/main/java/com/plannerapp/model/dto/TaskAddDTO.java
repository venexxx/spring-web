package com.plannerapp.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TaskAddDTO {
    @Size(min = 2,max = 50,message = "Description must be between 2 and 50 characters!")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Date must be in the future!")
    private LocalDate dueDate;


    @NotBlank(message = "Must to select Priority!")
    private String priority;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
