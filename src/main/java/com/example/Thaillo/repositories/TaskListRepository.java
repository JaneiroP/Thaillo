package com.example.Thaillo.repositories;

import com.example.Thaillo.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    Optional<TaskList> findByTitle(String title);
}
