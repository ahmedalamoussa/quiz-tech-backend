package com.quiztech.backend.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Column(nullable = false)
    private Double score; // En pourcentage (0-100)

    @Column(nullable = false, updatable = false)
    private LocalDateTime submittedAt;

    @Column(columnDefinition = "TEXT")
    private String answers; // JSON string des réponses

    @PrePersist
    protected void onCreate() {
        submittedAt = LocalDateTime.now();
    }
}