// src/main/java/com/quiztech/backend/dto/request/AnalysisRequest.java
package com.quiztech.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisRequest {
    private String quizName;
    private List<QuestionData> questions;
    private String studentName;
}