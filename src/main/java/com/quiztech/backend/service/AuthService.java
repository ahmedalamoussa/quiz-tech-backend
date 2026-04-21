package com.quiztech.backend.service;

import com.quiztech.backend.dto.request.LoginRequest;
import com.quiztech.backend.dto.request.RegisterRequest;
import com.quiztech.backend.dto.response.AuthResponse;
import com.quiztech.backend.entity.Role;
import com.quiztech.backend.entity.User;
import com.quiztech.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        // Vérifier si l'utilisateur existe déjà
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Créer l'utilisateur
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(request.getRole() != null ? request.getRole() : Role.STUDENT)
                .build();

        User savedUser = userRepository.save(user);

        return AuthResponse.builder()
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(savedUser.getRole().name())
                .message("User registered successfully")
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsernameOrEmail());

        if (userOpt.isEmpty()) {
            userOpt = userRepository.findByEmail(request.getUsernameOrEmail());
        }

        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid username or email");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return AuthResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .message("Login successful")
                .build();
    }
}