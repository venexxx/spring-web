package com.plannerapp.init;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.enums.PriorityName;
import com.plannerapp.repo.PriorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class PriorityInit implements CommandLineRunner {
    private final PriorityRepository priorityRepository;

    public PriorityInit(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Priority> priorities = new ArrayList<>();


        if (!(priorityRepository.count() > 0)){
            Arrays.stream(PriorityName.values()).forEach(p -> {
                Priority priority = new Priority();
                priority.setPriorityName(p);
                priorities.add(priority);


            });
            priorityRepository.saveAll(priorities);
        }
    }
}
