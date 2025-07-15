package com.example.Thaillo.dto;

import com.example.Thaillo.entities.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CardUpdateRequest {
    public String title;
    public String description;
    public Integer positionInList;
    public String isActive;
    public Long taskList_id;
    public Long createdBy_id;
    public LocalDate dueDate;
    public List<Long> assignedUsers = new ArrayList<>();
}
