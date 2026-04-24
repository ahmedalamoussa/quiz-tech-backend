package com.quiztech.backend.mapper;

import com.quiztech.backend.dto.request.SubmissionRequest;
import com.quiztech.backend.dto.response.SubmissionResponse;
import com.quiztech.backend.entity.Submission;
import org.springframework.stereotype.Component;

@Component
public class SubmissionMapper {

    public SubmissionResponse toResponse(Submission submission) {
        return SubmissionResponse.builder()
                .id(submission.getId())
                .username(submission.getUser().getUsername())
                .quizTitle(submission.getQuiz().getTitle())
                .score(submission.getScore())
                .answers(submission.getAnswers())
                .submittedAt(submission.getSubmittedAt())
                .build();
    }

    public Submission toEntity(SubmissionRequest request) {
        return Submission.builder()
                .score(request.getScore())
                .answers(request.getAnswers())
                .build();
    }
}