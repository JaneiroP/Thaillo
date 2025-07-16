package com.example.Thaillo.mappers;

import com.example.Thaillo.dto.CardCreateRequest;
import com.example.Thaillo.entities.Card;
import com.example.Thaillo.entities.TaskList;
import com.example.Thaillo.entities.User;
import org.springframework.stereotype.Component;

@Component
public class CardCreateRequestMapper {
    public Card toEntity(CardCreateRequest request, TaskList taskList, User user) {
        return Card.builder()
                .title(request.title)
                .taskList(taskList)
                .createdBy(user)
                .build();
    }
}
