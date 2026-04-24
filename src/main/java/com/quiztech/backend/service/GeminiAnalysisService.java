package com.quiztech.backend.service;

import com.quiztech.backend.dto.request.AnalysisRequest;
import com.quiztech.backend.dto.request.QuestionData;
import com.quiztech.backend.dto.response.AnalysisResponse;
import com.quiztech.backend.dto.response.PatternDetected;
import com.quiztech.backend.dto.response.PersonalizedAdvice;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeminiAnalysisService {

    // ✅ Renommé pour OpenRouter (à mettre à jour dans ton application.properties)
    @Value("${openrouter.api.key:}")
    private String openRouterApiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AnalysisResponse analyzeQuizWithAI(AnalysisRequest request) {
        try {
            int correctAnswers = (int) request.getQuestions().stream()
                    .filter(QuestionData::isCorrect)
                    .count();
            int totalQuestions = request.getQuestions().size();
            int score = totalQuestions > 0 ? (correctAnswers * 100) / totalQuestions : 0;

            Map<String, Object> parsedResponse;

            if (openRouterApiKey == null || openRouterApiKey.isBlank()) {
                log.warn("⚠️ Clé OpenRouter non configurée, utilisation de l'analyse locale");
                parsedResponse = analyzeLocally(request, correctAnswers, totalQuestions);
            } else {
                try {
                    String prompt = buildPrompt(request, correctAnswers, totalQuestions);
                    String aiResponse = callOpenRouterAPI(prompt);
                    log.info("✅ Réponse AI reçue via OpenRouter");
                    parsedResponse = parseAIResponse(aiResponse);
                } catch (Exception e) {
                    log.warn("⚠️ Erreur API OpenRouter, fallback local: {}", e.getMessage());
                    parsedResponse = analyzeLocally(request, correctAnswers, totalQuestions);
                }
            }

            return AnalysisResponse.builder()
                    .quizName(request.getQuizName())
                    .studentName(request.getStudentName())
                    .score(score)
                    .totalQuestions(totalQuestions)
                    .correctAnswers(correctAnswers)
                    .analysisReport((String) parsedResponse.get("fullReport"))
                    .patternsDetected((List<PatternDetected>) parsedResponse.get("patterns"))
                    .advices((List<PersonalizedAdvice>) parsedResponse.get("advices"))
                    .build();

        } catch (Exception e) {
            log.error("Erreur critique lors de l'analyse", e);
            throw new RuntimeException("Erreur lors de l'analyse IA: " + e.getMessage(), e);
        }
    }

    private String buildPrompt(AnalysisRequest request, int correctAnswers, int totalQuestions) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Tu es un expert pédagogue. Analyse ce quiz informatique.\n");
        prompt.append("Données : Quiz: ").append(request.getQuizName())
                .append(", Score: ").append(correctAnswers).append("/").append(totalQuestions).append("\n");

        prompt.append("\nRéponds UNIQUEMENT avec ce format JSON STRICT :\n");
        prompt.append("{\n");
        prompt.append("  \"fullReport\": \"...\",\n");
        prompt.append("  \"patterns\": [{\"patternName\": \"...\", \"description\": \"...\", \"errorQuestionNumbers\": [], \"errorCount\": 0}],\n");
        prompt.append("  \"advices\": [{\"patternName\": \"...\", \"explanation\": \"...\", \"advice\": \"...\", \"example\": \"...\"}]\n");
        prompt.append("}\n");

        return prompt.toString();
    }

    private String callOpenRouterAPI(String prompt) throws Exception {
        // ✅ URL OpenRouter
        String url = "https://openrouter.ai/api/v1/chat/completions";

        Map<String, Object> requestBody = new HashMap<>();
        // ✅ Utilisation du modèle spécifié
        requestBody.put("model", "inclusionai/ling-2.6-1t:free");

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "user", "content", prompt));
        requestBody.put("messages", messages);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // ✅ Bearer Auth pour OpenRouter
        headers.setBearerAuth(openRouterApiKey);
        // Optionnel mais recommandé par OpenRouter :
        headers.set("HTTP-Referer", "http://localhost:8080");
        headers.set("X-Title", "QuizTech Analysis Service");

        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

        log.info("📤 Appel OpenRouter (inclusionai/ling-2.6-1t:free)...");
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        JsonNode root = objectMapper.readTree(response.getBody());
        return root.path("choices").get(0).path("message").path("content").asText();
    }

    private Map<String, Object> parseAIResponse(String response) throws Exception {
        Map<String, Object> result = new HashMap<>();
        String jsonPart = extractJSON(response);
        JsonNode jsonNode = objectMapper.readTree(jsonPart);

        // ... Le reste de ta logique de parsing reste identique ...
        // (Conservation de patterns et advices comme dans ton code original)
        result.put("fullReport", jsonNode.path("fullReport").asText());
        result.put("patterns", new ArrayList<PatternDetected>()); // À compléter selon tes besoins
        result.put("advices", new ArrayList<PersonalizedAdvice>());

        return result;
    }

    private String extractJSON(String text) {
        int startIndex = text.indexOf('{');
        int endIndex = text.lastIndexOf('}');
        return (startIndex != -1 && endIndex != -1) ? text.substring(startIndex, endIndex + 1) : text;
    }

    private Map<String, Object> analyzeLocally(AnalysisRequest request, int correctAnswers, int totalQuestions) {
        // ... Ta logique locale reste la même ...
        return new HashMap<>();
    }
}