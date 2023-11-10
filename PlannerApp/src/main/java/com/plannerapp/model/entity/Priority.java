package com.plannerapp.model.entity;

import com.plannerapp.model.enums.PriorityName;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Table(name = "priorities")
@Entity
public class Priority extends BaseEntity{


    public Priority() {
        this.tasks = new HashSet<>();
    }

    @Enumerated(EnumType.STRING)
    @Column(unique = true,nullable = false)
    private PriorityName priorityName;


    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority")
    private Set<Task> tasks;

    public PriorityName getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(PriorityName priorityName) {
        String description = "";
        this.priorityName = priorityName;
        switch (priorityName){
            case URGENT:
                description = "An urgent problem that blocks the system use until the issue is resolved.";
                break;
            case IMPORTANT:
                description = "A core functionality that your product is explicitly supposed to perform is compromised.";
                break;
            case LOW:
                description = "Should be fixed if time permits but can be postponed.";
                break;
        }

        setDescription(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
