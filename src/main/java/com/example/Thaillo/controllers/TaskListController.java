package com.example.Thaillo.controllers;

import com.example.Thaillo.dto.TaskListRequest;
import com.example.Thaillo.entities.TaskList;
import com.example.Thaillo.repositories.TaskListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task_list")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListRepository taskListRepository;

    @GetMapping("/")
    public ResponseEntity<List<TaskList>> getTaskList() {
        List<TaskList> taskLists = taskListRepository.findAll();
        return ResponseEntity.ok(taskLists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TaskList>> getTaskList(@RequestBody Long id) {
        Optional<TaskList> taskList = taskListRepository.findById(id);
        return ResponseEntity.ok(taskList);
    }

    @PostMapping("/")
    public ResponseEntity<TaskList> postTaskList(@RequestBody TaskListRequest request) {

        TaskList taskList = TaskList.builder()
                .title(request.title)
                .background(request.background)
                .build();

        taskListRepository.save(taskList);

        return ResponseEntity.ok(taskList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskList> updateTaskList(
            @PathVariable Long id,
            @RequestBody TaskListRequest request) {

        Optional<TaskList> existing = taskListRepository.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TaskList taskList = existing.get();
        taskList.setTitle(request.title);
        taskList.setBackground(request.background);

        taskListRepository.save(taskList);
        return ResponseEntity.ok(taskList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable Long id) {
        if (!taskListRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        taskListRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }




}
