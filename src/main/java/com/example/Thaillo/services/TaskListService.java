package com.example.Thaillo.services;

import com.example.Thaillo.dto.TaskListRequest;
import com.example.Thaillo.entities.TaskList;
import com.example.Thaillo.entities.Board;
import com.example.Thaillo.repositories.BoardRepository;
import com.example.Thaillo.repositories.TaskListRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskListService {
    private final TaskListRepository taskListRepository;
    private final BoardRepository boardRepository;

    public List<TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }

    public Optional<TaskList> getTaskListById(Long id) {
        return taskListRepository.findById(id);
    }

    public TaskList createTaskList(TaskListRequest request) {
        Board board = boardRepository.findById(request.board_id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found"));

        TaskList taskList = TaskList.builder()
                .title(request.title)
                .background(request.background)
                .board(board)
                .build();
        return taskListRepository.save(taskList);
    }

    public Optional<TaskList> updateTaskList(Long id, TaskListRequest request) {
        Optional<TaskList> existing = taskListRepository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }
        Board board = boardRepository.findById(request.board_id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found"));

        TaskList taskList = existing.get();
        taskList.setTitle(request.title);
        taskList.setBackground(request.background);
        taskList.setBoard(board);
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
