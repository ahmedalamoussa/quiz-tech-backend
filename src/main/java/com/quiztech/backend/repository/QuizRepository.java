package com.quiztech.backend.repository;

import com.quiztech.backend.entity.Quiz;
import com.quiztech.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCreatedBy(User createdBy);
    List<Quiz> findByCategory(String category);
    List<Quiz> findAll();
}