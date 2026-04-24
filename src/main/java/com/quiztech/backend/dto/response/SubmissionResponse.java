package com.quiztech.backend.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionResponse {

    private Long id;
    private String username;
    private String quizTitle;
    private Double score;
    private String answers;
    private LocalDateTime submittedAt;
}