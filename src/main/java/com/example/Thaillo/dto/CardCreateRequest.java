package com.example.Thaillo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CardCreateRequest {
    @NotBlank(message = "Title is required")
    public String title;

    @NotNull(message = "TaskList id is required")
    public Long taskList_id;

    @NotNull(message = "CreatedBy id is required")
    public Long createdBy_id;
}
