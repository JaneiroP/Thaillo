package com.example.Thaillo.services;

import com.example.Thaillo.dto.CommentRequest;
import com.example.Thaillo.entities.Comment;
import com.example.Thaillo.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    public final CommentRepository commentRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id){
        return commentRepository.findById(id);
    }

    public Comment createComment(CommentRequest request) {
        Comment comment = Comment.builder()
                .card(request.card)
                .author(request.author)
                .content(request.content)
                .build();
        return commentRepository.save(comment);
    }

    public Optional<Comment> updateComment(Long id, CommentRequest request) {
        Optional<Comment> existing = commentRepository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }
        Comment comment = existing.get();
        comment.setCard(request.card);
        comment.setAuthor(request.author);
        comment.setContent(request.content);
        commentRepository.save(comment);
        return Optional.of(comment);
    }

    public boolean deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            return false;
        }
        commentRepository.deleteById(id);
        return true;
    }

}
