package com.example.Thaillo.mappers;

import com.example.Thaillo.dto.CardUpdateRequest;
import com.example.Thaillo.entities.Card;
import com.example.Thaillo.entities.TaskList;
import com.example.Thaillo.entities.User;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CardUpdateRequestMapper {
    public void updateEntity(Card card, CardUpdateRequest request, TaskList taskList, User user, List<User> assignedUsers) {
        card.setTitle(request.title);
        card.setDescription(request.description);
        card.setPositionInList(request.positionInList);
        card.setIsActive(request.isActive);
        card.setTaskList(taskList);
        card.setCreatedBy(user);
        card.setDueDate(request.dueDate);
        card.setAssignedUsers(assignedUsers);
    }
}
