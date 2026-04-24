package com.quiztech.backend.service;

import com.quiztech.backend.entity.*;
import com.quiztech.backend.exception.ResourceNotFoundException;
import com.quiztech.backend.repository.SubmissionRepository;
import com.quiztech.backend.repository.QuestionRepository;
import com.quiztech.backend.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    // Créer une soumission
    public Submission createSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }

    // Récupérer une soumission par ID
    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with id: " + id));
    }

    // Récupérer les soumissions d'un utilisateur
    public List<Submission> getSubmissionsByUser(User user) {
        return submissionRepository.findByUser(user);
    }

    // Récupérer les soumissions d'un quiz
    public List<Submission> getSubmissionsByQuiz(Quiz quiz) {
        return submissionRepository.findByQuiz(quiz);
    }

    // Récupérer la dernière soumission d'un utilisateur pour un quiz
    public Submission getLastSubmissionByUserAndQuiz(User user, Quiz quiz) {
        List<Submission> submissions = submissionRepository.findByUserAndQuizOrderBySubmittedAtDesc(user, quiz);
        if (submissions.isEmpty()) {
            throw new ResourceNotFoundException("No submission found for user and quiz");
        }
        return submissions.get(0);
    }

    // Calculer le score d'une soumission
    public Double calculateScore(Submission submission) {
        return submission.getScore();
    }

    // Supprimer une soumission
    public void deleteSubmission(Long id) {
        Submission submission = getSubmissionById(id);
        submissionRepository.deleteById(id);
    }
}