// src/main/java/com/quiztech/backend/dto/response/PatternDetected.java
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
public class PatternDetected {
    private String patternName;
    private String description;
    private List<Integer> errorQuestionNumbers;
    private int errorCount;
}