package com.quiztech.backend.repository;

import com.quiztech.backend.entity.Answer;
import com.quiztech.backend.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestion(Question question);
    List<Answer> findByQuestionOrderByOrderIndex(Question question);
}