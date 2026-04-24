package com.quiztech.backend.controller;

import com.quiztech.backend.dto.request.LoginRequest;
import com.quiztech.backend.dto.request.RegisterRequest;
import com.quiztech.backend.dto.response.AuthResponse;
import com.quiztech.backend.service.AuthService;
import com.quiztech.backend.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    AuthResponse.builder()
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    AuthResponse.builder()
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        try {
            String cleanToken = token.replace("Bearer ", "");
            String username = jwtService.extractUsername(cleanToken);
            boolean isValid = jwtService.isTokenValid(cleanToken, username);
            return ResponseEntity.ok(isValid);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}