package com.quiztech.backend.repository;

import com.quiztech.backend.entity.Question;
import com.quiztech.backend.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuiz(Quiz quiz);
    List<Question> findByQuizOrderByOrderIndex(Quiz quiz);
}