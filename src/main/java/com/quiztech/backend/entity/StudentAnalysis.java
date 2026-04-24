package com.quiztech.backend.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class StudentAnalysis {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private Quiz quiz;

    private Double score;
    private String errorPatterns;  // JSON des patterns détectés
    private String aiAdvice;       // Les conseils de l'IA
    private LocalDateTime analyzedAt;
}