package com.plannerapp.repo;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.enums.PriorityName;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PriorityRepository extends JpaRepository<Priority,Long> {


    Priority findByPriorityName(PriorityName priorityName);
}
