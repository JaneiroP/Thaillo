package com.example.Thaillo.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

@Builder
public class CardResponse {
    public Long id;
    public String title;
    public String description;
    public Integer positionInList;
    public String isActive;
    public Long task_list_id;
    public Long author_id;
    public LocalDate dueDate;
    public List<Long> assignedUsers;
}
