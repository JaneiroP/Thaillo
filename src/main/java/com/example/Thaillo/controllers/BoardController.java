package com.example.Thaillo.controllers;

import com.example.Thaillo.dto.BoardRequest;
import com.example.Thaillo.entities.Board;
import com.example.Thaillo.repositories.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Board>> getBoard(@RequestBody Long id) {
        System.out.println("Boards");
        Optional<Board> board = boardRepository.findById(id);
        return ResponseEntity.ok(board);
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

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(
            @PathVariable Long id,
            @RequestBody BoardRequest request) {

        Optional<Board> existing = boardRepository.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Board board = existing.get();
        board.setTitle(request.title);
        board.setDescription(request.description);
        board.setBackground(request.background);

        boardRepository.save(board);
        return ResponseEntity.ok(board);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        if (!boardRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        boardRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }




}
