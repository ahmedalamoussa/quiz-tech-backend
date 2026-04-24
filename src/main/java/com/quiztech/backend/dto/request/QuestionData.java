// src/main/java/com/quiztech/backend/dto/request/QuestionData.java
package com.quiztech.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionData {
    private Long questionId;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;      // A, B, C, D
    private String studentAnswer;       // A, B, C, D
    private boolean isCorrect;
}