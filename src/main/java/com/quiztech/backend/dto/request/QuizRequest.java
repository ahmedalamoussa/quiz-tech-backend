package com.quiztech.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 1, message = "Difficulty must be between 1 and 5")
    @Max(value = 5, message = "Difficulty must be between 1 and 5")
    private Integer difficulty;
}