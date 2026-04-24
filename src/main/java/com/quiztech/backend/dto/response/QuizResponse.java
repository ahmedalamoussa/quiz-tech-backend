package com.quiztech.backend.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResponse {

    private Long id;
    private String title;
    private String description;
    private String category;
    private Integer difficulty;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer questionCount;
}