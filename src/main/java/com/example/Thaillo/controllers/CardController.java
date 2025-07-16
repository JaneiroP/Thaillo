package com.example.Thaillo.controllers;

import com.example.Thaillo.dto.CardCreateRequest;
import com.example.Thaillo.dto.CardUpdateRequest;
import com.example.Thaillo.dto.CardResponse;
import com.example.Thaillo.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;


    @GetMapping("/")
    public ResponseEntity<List<CardResponse>> getCards() {
        List<CardResponse> cards = cardService.getAllCards();
        return ResponseEntity.ok(cards);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CardResponse> getCard(@PathVariable Long id) {
        Optional<CardResponse> card = cardService.getCardById(id);
        return card.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/")
    public ResponseEntity<CardResponse> postCard(@RequestBody CardCreateRequest request) {
        CardResponse card = cardService.createCard(request);
        return ResponseEntity.ok(card);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CardResponse> updateCard(
            @PathVariable Long id,
            @RequestBody CardUpdateRequest request) {
        Optional<CardResponse> updated = cardService.updateCard(id, request);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        boolean deleted = cardService.deleteCard(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
