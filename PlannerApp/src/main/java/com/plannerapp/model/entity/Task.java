package com.plannerapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Table(name = "tasks")
@Entity
public class Task extends BaseEntity{

    @Column(nullable = false)
    @Size(min = 2,max = 50)
    private String description;


    @Column(nullable = false)
    @Future
    private LocalDate dueDate;

    @ManyToOne
    @NotNull
    private Priority priority;

    @ManyToOne
    private User user;

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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
