package com.quiztech.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Quiz ID is required")
    private Long quizId;

    @NotNull(message = "Score is required")
    private Double score;

    private String answers; // JSON string des réponses
}