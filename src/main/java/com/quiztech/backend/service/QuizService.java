package com.quiztech.backend.service;

import com.quiztech.backend.entity.Quiz;
import com.quiztech.backend.entity.User;
import com.quiztech.backend.exception.ResourceNotFoundException;
import com.quiztech.backend.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    // Créer un quiz
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    // Récupérer tous les quiz
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // Récupérer un quiz par ID
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));
    }

    // Récupérer les quiz créés par un utilisateur
    public List<Quiz> getQuizzesByCreator(User creator) {
        return quizRepository.findByCreatedBy(creator);
    }

    // Récupérer les quiz par catégorie
    public List<Quiz> getQuizzesByCategory(String category) {
        return quizRepository.findByCategory(category);
    }

    // Mettre à jour un quiz
    public Quiz updateQuiz(Long id, Quiz quizDetails) {
        Quiz quiz = getQuizById(id);
        quiz.setTitle(quizDetails.getTitle());
        quiz.setDescription(quizDetails.getDescription());
        quiz.setCategory(quizDetails.getCategory());
        quiz.setDifficulty(quizDetails.getDifficulty());
        return quizRepository.save(quiz);
    }

    // Supprimer un quiz
    public void deleteQuiz(Long id) {
        Quiz quiz = getQuizById(id);
        quizRepository.deleteById(id);
    }
}