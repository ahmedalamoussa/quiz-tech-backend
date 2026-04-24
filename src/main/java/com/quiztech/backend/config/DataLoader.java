package com.quiztech.backend.config;

import com.quiztech.backend.data.QuizData;
import com.quiztech.backend.entity.User;
import com.quiztech.backend.repository.QuizRepository;
import com.quiztech.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si les quiz existent déjà
        if (quizRepository.count() == 0) {
            // Récupérer ou créer l'utilisateur "admin"
            User admin = userRepository.findByUsername("ahmed")
                    .orElse(null);

            if (admin != null) {
                // Insérer tous les quiz
                quizRepository.saveAll(QuizData.getQuizzes(admin));
                System.out.println("✅ 50 quizzes loaded successfully!");
            } else {
                System.out.println("⚠️ User 'ahmed' not found. Please register first.");
            }
        } else {
            System.out.println("ℹ️ Quizzes already exist in database.");
        }
    }
}