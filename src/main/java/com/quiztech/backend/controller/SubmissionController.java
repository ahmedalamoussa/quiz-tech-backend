package com.quiztech.backend.controller;

import com.quiztech.backend.dto.request.SubmissionRequest;
import com.quiztech.backend.dto.response.SubmissionResponse;
import com.quiztech.backend.entity.Submission;
import com.quiztech.backend.entity.User;
import com.quiztech.backend.entity.Quiz;
import com.quiztech.backend.mapper.SubmissionMapper;
import com.quiztech.backend.repository.UserRepository;
import com.quiztech.backend.repository.QuizRepository;
import com.quiztech.backend.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final SubmissionMapper submissionMapper;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    // Créer une soumission
    @PostMapping
    public ResponseEntity<SubmissionResponse> createSubmission(@Valid @RequestBody SubmissionRequest request) {
        try {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Quiz quiz = quizRepository.findById(request.getQuizId())
                    .orElseThrow(() -> new RuntimeException("Quiz not found"));

            Submission submission = submissionMapper.toEntity(request);
            submission.setUser(user);
            submission.setQuiz(quiz);

            Submission createdSubmission = submissionService.createSubmission(submission);

            return ResponseEntity.status(HttpStatus.CREATED).body(submissionMapper.toResponse(createdSubmission));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Récupérer une soumission par ID
    @GetMapping("/{id}")
    public ResponseEntity<SubmissionResponse> getSubmissionById(@PathVariable Long id) {
        try {
            Submission submission = submissionService.getSubmissionById(id);
            return ResponseEntity.ok(submissionMapper.toResponse(submission));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Récupérer les soumissions d'un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SubmissionResponse>> getSubmissionsByUser(@PathVariable Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            List<Submission> submissions = submissionService.getSubmissionsByUser(user);
            List<SubmissionResponse> responses = submissions.stream()
                    .map(submissionMapper::toResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Récupérer les soumissions d'un quiz
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<SubmissionResponse>> getSubmissionsByQuiz(@PathVariable Long quizId) {
        try {
            Quiz quiz = quizRepository.findById(quizId)
                    .orElseThrow(() -> new RuntimeException("Quiz not found"));
            List<Submission> submissions = submissionService.getSubmissionsByQuiz(quiz);
            List<SubmissionResponse> responses = submissions.stream()
                    .map(submissionMapper::toResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Récupérer la dernière soumission d'un utilisateur pour un quiz
    @GetMapping("/user/{userId}/quiz/{quizId}")
    public ResponseEntity<SubmissionResponse> getLastSubmissionByUserAndQuiz(
            @PathVariable Long userId,
            @PathVariable Long quizId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Quiz quiz = quizRepository.findById(quizId)
                    .orElseThrow(() -> new RuntimeException("Quiz not found"));
            Submission submission = submissionService.getLastSubmissionByUserAndQuiz(user, quiz);
            return ResponseEntity.ok(submissionMapper.toResponse(submission));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une soumission
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
        try {
            submissionService.deleteSubmission(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}