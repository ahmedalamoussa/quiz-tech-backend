package com.quiztech.backend.mapper;

import com.quiztech.backend.dto.request.RegisterRequest;
import com.quiztech.backend.dto.response.AuthResponse;
import com.quiztech.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
    }

    public AuthResponse toAuthResponse(User user) {
        return AuthResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}