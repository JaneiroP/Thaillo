package com.example.Thaillo.controllers;

import com.example.Thaillo.dto.AuthResponse;
import com.example.Thaillo.dto.BoardRequest;
import com.example.Thaillo.dto.RegisterRequest;
import com.example.Thaillo.entities.Board;
import com.example.Thaillo.entities.User;
import com.example.Thaillo.repositories.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/")
    public ResponseEntity<List<Board>> getBoards() {
        System.out.println("Boards");
        List<Board> boards = boardRepository.findAll();
        return ResponseEntity.ok(boards);
    }

    @PostMapping("/")
    public ResponseEntity<Board> postBoard(@RequestBody BoardRequest request) {

        Board board = Board.builder()
                .title(request.title)
                .description(request.description)
                .background(request.background)
                .build();

        boardRepository.save(board);

        return ResponseEntity.ok(board);
    }



}
