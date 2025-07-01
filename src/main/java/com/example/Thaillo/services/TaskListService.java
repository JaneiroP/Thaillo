package com.example.Thaillo.services;

import com.example.Thaillo.dto.TaskListRequest;
import com.example.Thaillo.entities.TaskList;
import com.example.Thaillo.repositories.TaskListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskListService {
    private final TaskListRepository taskListRepository;

    public List<TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }

    public Optional<TaskList> getTaskListById(Long id) {
        return taskListRepository.findById(id);
    }

    public TaskList createTaskList(TaskListRequest request) {
        TaskList taskList = TaskList.builder()
                .title(request.title)
                .background(request.background)
                .build();
        return taskListRepository.save(taskList);
    }

    public Optional<TaskList> updateTaskList(Long id, TaskListRequest request) {
        Optional<TaskList> existing = taskListRepository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }
        TaskList taskList = existing.get();
        taskList.setTitle(request.title);
        taskList.setBackground(request.background);
        taskListRepository.save(taskList);
        return Optional.of(taskList);
    }

    public boolean deleteTaskList(Long id) {
        if (!taskListRepository.existsById(id)) {
            return false;
        }
        taskListRepository.deleteById(id);
        return true;
    }

}
