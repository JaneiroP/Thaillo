package com.example.Thaillo.services;

import com.example.Thaillo.dto.BoardRequest;
import com.example.Thaillo.entities.Board;
import com.example.Thaillo.repositories.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public Board createBoard(BoardRequest request) {
        Board board = Board.builder()
                .title(request.title)
                .description(request.description)
                .background(request.background)
                .build();
        return boardRepository.save(board);
    }

    public Optional<Board> updateBoard(Long id, BoardRequest request) {
        Optional<Board> existing = boardRepository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }
        Board board = existing.get();
        board.setTitle(request.title);
        board.setDescription(request.description);
        board.setBackground(request.background);
        boardRepository.save(board);
        return Optional.of(board);
    }

    public boolean deleteBoard(Long id) {
        if (!boardRepository.existsById(id)) {
            return false;
        }
        boardRepository.deleteById(id);
        return true;
    }
}
