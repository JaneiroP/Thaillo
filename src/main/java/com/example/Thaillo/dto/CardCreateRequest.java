package com.example.Thaillo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CardCreateRequest {
    @NotBlank(message = "Title is required")
    public String title;

    @NotNull(message = "TaskList id is required")
    public Long task_list_id;

    @NotNull(message = "Author id is required")
    public Long author_id;
}
