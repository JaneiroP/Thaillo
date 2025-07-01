package com.example.Thaillo.controllers;

import com.example.Thaillo.dto.TaskListRequest;
import com.example.Thaillo.entities.TaskList;
import com.example.Thaillo.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task_list")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;

    @GetMapping("/")
    public ResponseEntity<List<TaskList>> getTaskList() {
        List<TaskList> taskLists = taskListService.getAllTaskLists();
        return ResponseEntity.ok(taskLists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TaskList>> getTaskList(@RequestBody Long id) {
        Optional<TaskList> taskList = taskListService.getTaskListById(id);
        return ResponseEntity.ok(taskList);
    }

    @PostMapping("/")
    public ResponseEntity<TaskList> postTaskList(@RequestBody TaskListRequest request) {
        TaskList taskList = taskListService.createTaskList(request);
        return ResponseEntity.ok(taskList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskList> updateTaskList(
            @PathVariable Long id,
            @RequestBody TaskListRequest request) {
        Optional<TaskList> taskList = taskListService.updateTaskList(id,request);
        return taskList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable Long id) {
        boolean deleted = taskListService.deleteTaskList(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }




}
