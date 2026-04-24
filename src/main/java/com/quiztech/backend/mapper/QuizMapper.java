package com.quiztech.backend.mapper;

import com.quiztech.backend.dto.request.QuizRequest;
import com.quiztech.backend.dto.response.QuizResponse;
import com.quiztech.backend.entity.Quiz;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public Quiz toEntity(QuizRequest request, com.quiztech.backend.entity.User createdBy) {
        return Quiz.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .difficulty(request.getDifficulty())
                .createdBy(createdBy)
                .build();
    }

    public QuizResponse toResponse(Quiz quiz) {
        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .category(quiz.getCategory())
                .difficulty(quiz.getDifficulty())
                .createdBy(quiz.getCreatedBy().getUsername())
                .createdAt(quiz.getCreatedAt())
                .updatedAt(quiz.getUpdatedAt())
                .questionCount(quiz.getQuestions() != null ? quiz.getQuestions().size() : 0)
                .build();
    }

    public Quiz updateEntity(QuizRequest request, Quiz quiz) {
        quiz.setTitle(request.getTitle());
        quiz.setDescription(request.getDescription());
        quiz.setCategory(request.getCategory());
        quiz.setDifficulty(request.getDifficulty());
        return quiz;
    }
}