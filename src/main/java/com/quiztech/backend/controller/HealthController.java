package com.quiztech.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")           // Garde ça
@CrossOrigin(origins = "*")          // ← Changement important : on autorise tout pour le moment
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Backend is running successfully! ✅");
    }

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("QuizTech Backend v1.0 - OK");
    }
}