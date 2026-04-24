// src/main/java/com/quiztech/backend/dto/response/PersonalizedAdvice.java
package com.quiztech.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalizedAdvice {
    private String patternName;
    private String explanation;
    private String advice;
    private String example;
}