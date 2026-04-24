package com.quiztech.backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(nullable = false)
    private Boolean isCorrect;

    @Column(nullable = false)
    private Integer orderIndex; // Pour ordonner les réponses

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}
