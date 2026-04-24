package com.quiztech.backend.controller;

import com.quiztech.backend.dto.request.QuizRequest;
import com.quiztech.backend.dto.response.QuizResponse;
import com.quiztech.backend.entity.Quiz;
import com.quiztech.backend.entity.User;
import com.quiztech.backend.mapper.QuizMapper;
import com.quiztech.backend.repository.UserRepository;
import com.quiztech.backend.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class QuizController {

    private final QuizService quizService;
    private final QuizMapper quizMapper;
    private final UserRepository userRepository;

    // Créer un quiz
    @PostMapping
    public ResponseEntity<QuizResponse> createQuiz(
            @Valid @RequestBody QuizRequest request) {
        try {
            // Pour maintenant, utilise l'utilisateur avec ID 1
            User user = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Quiz quiz = quizMapper.toEntity(request, user);
            Quiz createdQuiz = quizService.createQuiz(quiz);

            return ResponseEntity.status(HttpStatus.CREATED).body(quizMapper.toResponse(createdQuiz));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // Récupérer tous les quiz
    @GetMapping
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {
        try {
            List<Quiz> quizzes = quizService.getAllQuizzes();
            List<QuizResponse> responses = quizzes.stream()
                    .map(quizMapper::toResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Récupérer un quiz par ID
    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable Long id) {
        try {
            Quiz quiz = quizService.getQuizById(id);
            return ResponseEntity.ok(quizMapper.toResponse(quiz));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Récupérer les quiz par catégorie
    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuizResponse>> getQuizzesByCategory(@PathVariable String category) {
        try {
            List<Quiz> quizzes = quizService.getQuizzesByCategory(category);
            List<QuizResponse> responses = quizzes.stream()
                    .map(quizMapper::toResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Mettre à jour un quiz
    @PutMapping("/{id}")
    public ResponseEntity<QuizResponse> updateQuiz(
            @PathVariable Long id,
            @Valid @RequestBody QuizRequest request) {
        try {
            Quiz quiz = quizService.getQuizById(id);
            Quiz updatedQuiz = quizMapper.updateEntity(request, quiz);
            Quiz saved = quizService.createQuiz(updatedQuiz);
            return ResponseEntity.ok(quizMapper.toResponse(saved));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un quiz
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        try {
            quizService.deleteQuiz(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}