package com.example.Thaillo.services;

import com.example.Thaillo.dto.CardRequest;
import com.example.Thaillo.dto.CardUpdateRequest;
import com.example.Thaillo.entities.Board;
import com.example.Thaillo.entities.Card;
import com.example.Thaillo.entities.TaskList;
import com.example.Thaillo.entities.User;
import com.example.Thaillo.repositories.CardRepository;
import com.example.Thaillo.repositories.TaskListRepository;
import com.example.Thaillo.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    public final CardRepository cardRepository;
    public final TaskListRepository taskListRepository;
    public final UserRepository userRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }

    public Card createCard(CardRequest request) {
        TaskList taskList = getTaskListOrThrow(request.taskList_id);
        User user = getUserOrThrow(request.createdBy_id);

        Card card = Card.builder()
                .title(request.title)
                .taskList(taskList)
                .createdBy(user)
                .build();
        return cardRepository.save(card);
    }

    public Optional<Card> updateCard(Long id, CardUpdateRequest request) {
        Optional<Card> existing = cardRepository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }
        Card card = existing.get();
        TaskList taskList = getTaskListOrThrow(request.taskList_id);
        User user = getUserOrThrow(request.createdBy_id);
        card.setTitle(request.title);
        card.setDescription(request.description);
        card.setPositionInList(request.positionInList);
        card.setIsActive(request.isActive);
        card.setTaskList(taskList);
        card.setCreatedBy(user);
        card.setDueDate(request.dueDate);
        List<User> assignedUsers = new java.util.ArrayList<>();
        if (request.assignedUsers != null) {
            for (Long userId : request.assignedUsers) {
                assignedUsers.add(getUserOrThrow(userId));
            }
        }
        card.setAssignedUsers(assignedUsers);
        cardRepository.save(card);
        return Optional.of(card);
    }
    private TaskList getTaskListOrThrow(Long id) {
        return taskListRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaskList not found"));
    }

    private User getUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public boolean deleteCard(Long id) {
        if (!cardRepository.existsById(id)) {
            return false;
        }
        cardRepository.deleteById(id);
        return true;
    }
}
