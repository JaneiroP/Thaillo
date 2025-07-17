package com.example.Thaillo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CardUpdateRequest {
    @NotBlank(message = "Title is required")
    public String title;

    public String description;

    public Integer positionInList;

    @NotBlank(message = "isActive is required")
    public String isActive;

    @NotNull(message = "TaskList id is required")
    public Long task_list_id;

    @NotNull(message = "Author id is required")
    public Long author_id;

    public LocalDate dueDate;

    public List<Long> assignedUsers = new ArrayList<>();
}
