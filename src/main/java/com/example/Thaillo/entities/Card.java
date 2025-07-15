package com.example.Thaillo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private Integer positionInList;

    private String isActive;

    @ManyToOne
    @JoinColumn(name = "taskList_id", nullable = false)
    @JsonBackReference
    private TaskList taskList;

    @ManyToOne
    @JoinColumn(name = "createdBy_id", nullable = false)
    @JsonBackReference
    private User createdBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    private LocalDate dueDate;

    @ManyToMany
    private List<User> assignedUsers = new ArrayList<>();
}

