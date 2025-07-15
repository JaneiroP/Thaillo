package com.example.Thaillo.dto;

import com.example.Thaillo.entities.Card;
import com.example.Thaillo.entities.User;

public class CommentRequest {
    public Card card;
    public User author;
    public String content;
}
