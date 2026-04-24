// src/main/java/com/quiztech/backend/controller/AnalysisController.java
package com.quiztech.backend.controller;

import com.quiztech.backend.dto.request.AnalysisRequest;
import com.quiztech.backend.dto.response.AnalysisResponse;
import com.quiztech.backend.service.GeminiAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/analysis")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AnalysisController {

    private final GeminiAnalysisService geminiAnalysisService;

    @PostMapping("/quiz-analysis")
    public ResponseEntity<AnalysisResponse> analyzeQuiz(@RequestBody AnalysisRequest request) {
        AnalysisResponse response = geminiAnalysisService.analyzeQuizWithAI(request);
        return ResponseEntity.ok(response);
    }
}