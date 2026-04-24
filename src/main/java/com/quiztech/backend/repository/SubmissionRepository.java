package com.quiztech.backend.repository;

import com.quiztech.backend.entity.Submission;
import com.quiztech.backend.entity.Quiz;
import com.quiztech.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByUser(User user);
    List<Submission> findByQuiz(Quiz quiz);
    Optional<Submission> findByUserAndQuiz(User user, Quiz quiz);
    List<Submission> findByUserAndQuizOrderBySubmittedAtDesc(User user, Quiz quiz);
}