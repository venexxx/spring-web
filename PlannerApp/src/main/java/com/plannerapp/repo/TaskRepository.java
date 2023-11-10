package com.plannerapp.repo;

import com.plannerapp.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository

public interface TaskRepository extends JpaRepository<Task,Long> {
    Set<Task> findAllByUserId(Long id);

    @Query(
            value = "SELECT * FROM TASKS t WHERE t.user_id is null",
            nativeQuery = true)
    Set<Task> findAllByUserIdIsNotIn();


    Optional<Task> findById(Long aLong);
}
