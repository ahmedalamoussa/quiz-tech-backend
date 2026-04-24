// src/main/java/com/quiztech/backend/dto/response/AnalysisResponse.java
package com.quiztech.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisResponse {
    private String quizName;
    private String studentName;
    private int score;
    private int totalQuestions;
    private int correctAnswers;
    private String analysisReport;
    private List<PatternDetected> patternsDetected;
    private List<PersonalizedAdvice> advices;
}