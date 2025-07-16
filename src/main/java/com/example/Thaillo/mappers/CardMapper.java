package com.example.Thaillo.mappers;

import com.example.Thaillo.dto.CardResponse;
import com.example.Thaillo.entities.Card;
import com.example.Thaillo.entities.User;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    public CardResponse toResponse(Card card) {
        return CardResponse.builder()
                .id(card.getId())
                .title(card.getTitle())
                .description(card.getDescription())
                .positionInList(card.getPositionInList())
                .isActive(card.getIsActive())
                .taskList_id(card.getTaskList() != null ? card.getTaskList().getId() : null)
                .createdBy_id(card.getCreatedBy() != null ? card.getCreatedBy().getId() : null)
                .dueDate(card.getDueDate())
                .assignedUsers(card.getAssignedUsers() != null ? card.getAssignedUsers().stream().map(User::getId).toList() : java.util.Collections.emptyList())
                .build();
    }
}
