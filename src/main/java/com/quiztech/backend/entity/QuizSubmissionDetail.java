package com.quiztech.backend.entity;

import jakarta.persistence.*;

@Entity
public class QuizSubmissionDetail {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Submission submission;

    @ManyToOne
    private Question question;

    private String studentAnswer;
    private String correctAnswer;
    private Boolean isCorrect;
}