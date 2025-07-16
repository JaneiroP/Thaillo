package com.example.Thaillo.services;

import com.example.Thaillo.dto.CardCreateRequest;
import com.example.Thaillo.dto.CardUpdateRequest;
import com.example.Thaillo.entities.Card;
import com.example.Thaillo.entities.TaskList;
import com.example.Thaillo.entities.User;
import com.example.Thaillo.dto.CardResponse;
import com.example.Thaillo.mappers.CardMapper;
import com.example.Thaillo.mappers.CardCreateRequestMapper;
import com.example.Thaillo.mappers.CardUpdateRequestMapper;
import com.example.Thaillo.repositories.CardRepository;
import com.example.Thaillo.repositories.TaskListRepository;
import com.example.Thaillo.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final TaskListRepository taskListRepository;
    private final UserRepository userRepository;
    private final CardMapper cardMapper;
    private final CardCreateRequestMapper cardCreateRequestMapper;
    private final CardUpdateRequestMapper cardUpdateRequestMapper;

    private static final Logger logger = LoggerFactory.getLogger(CardService.class);


    public List<CardResponse> getAllCards() {
         //Fetch all records in Card entity and map each one applying
         //mapper to get its info and finally convert to list
        return cardRepository.findAll().stream().map(cardMapper::toResponse).toList();
    }

    public Optional<CardResponse> getCardById(Long id) {
        //Fetch one record in Card entity and apply mapper to get its info
        return cardRepository.findById(id).map(cardMapper::toResponse);
    }

    // Do not save in DB if some error occur
    @org.springframework.transaction.annotation.Transactional
    public CardResponse createCard(CardCreateRequest request) {
        logger.info("Creating card with title: {} for taskListId: {} by userId: {}", request.title, request.taskList_id, request.createdBy_id);
        // Get TaskList and User objects by ids provided by user
        TaskList taskList = getTaskListOrThrow(request.taskList_id);
        User user = getUserOrThrow(request.createdBy_id);
        // Use cardCreateRequestMapper toEntity method for insert only defined parameters
        Card card = cardCreateRequestMapper.toEntity(request, taskList, user);
        // Save in DB
        card = cardRepository.save(card);
        logger.debug("Card created with id: {}", card.getId());
        // Return defined response using cardMapper toResponse method for
        // show only defined info
        return cardMapper.toResponse(card);
    }

    @org.springframework.transaction.annotation.Transactional
    public Optional<CardResponse> updateCard(Long id, CardUpdateRequest request) {
        // Ensure card for edit exist
        Optional<Card> existing = cardRepository.findById(id);
        if (existing.isEmpty()) {
            logger.warn("Update failed: No card found with id: {}. Cannot update non-existent card.", id);
            return Optional.empty();
        }
        Card card = existing.get();
        TaskList taskList = getTaskListOrThrow(request.taskList_id);
        User user = getUserOrThrow(request.createdBy_id);
        List<User> assignedUsers = getAssignedUsers(request.assignedUsers);
        cardUpdateRequestMapper.updateEntity(card, request, taskList, user, assignedUsers);
        cardRepository.save(card);
        return Optional.of(cardMapper.toResponse(card));
    }

    // Return taskList searched by id or throw
    private TaskList getTaskListOrThrow(Long id) {
        return taskListRepository.findById(id)
                .orElseThrow(() -> {
                    String msg = "No TaskList found with id: " + id + ". Please verify the task list exists.";
                    logger.error(msg);
                    return new EntityNotFoundException(msg);
                });
    }

    // Return user searched by id or throw
    private User getUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    String msg = "No User found with id: " + id + ". Please verify the user exists.";
                    logger.error(msg);
                    return new EntityNotFoundException(msg);
                });
    }

    // Return users searched by ids or throw
    private List<User> getAssignedUsers(List<Long> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return java.util.Collections.emptyList();
        }
        List<User> users = new java.util.ArrayList<>();
        for (Long userId : userIds) {
            users.add(getUserOrThrow(userId));
        }
        return users;
    }

    @org.springframework.transaction.annotation.Transactional
    public boolean deleteCard(Long id) {
        if (!cardRepository.existsById(id)) {
            logger.warn("Delete failed: No card found with id: {}. Cannot delete non-existent card.", id);
            return false;
        }
        // Delete card by id
        cardRepository.deleteById(id);
        logger.info("Deleted card with id: {}", id);
        return true;
    }
}
