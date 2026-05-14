package com.farm.backend.domain.chat.service;

import com.farm.backend.domain.chat.dto.ChatRequestDto;
import com.farm.backend.domain.chat.dto.ChatResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiService {

    // 외부 API(Google)와 통신하기 위한 스프링 부트 내장 도구
    private final RestTemplate restTemplate = new RestTemplate();

    // application-secret.yml에 숨겨둔 키 발급
    @Value("${gemini.api.key}")
    private String apiKey;

    // application.yaml에 적어둔 구글 API 요청 주소
    @Value("${gemini.api.url}")
    private String apiUrl;

    public ChatResponseDto getChatResponse(ChatRequestDto requestDto) {

        // [1] 프롬프트 엔지니어링 (AI에게 고령 농업인 맞춤 페르소나 부여)
        // ⚠ 현재 테스트용 더미 데이터로 대체, 추후 DB가 연결될 시, 진짜 작물/지역으로 교체 필요.
        String crop = "사과";
        String region = "경북 안동";

        String prompt = String.format(
                "당신은 %s 지역에서 %s 농사를 짓는 고령 농업인을 돕는 AI 조수입니다. " +
                        "어르신이 이해하기 쉽도록 농업 전문 용어를 최대한 풀어서 설명하고, 3문장 이내로 아주 친절하게 답해주세요.\n\n" +
                        "어르신의 질문: %s",
                region, crop, requestDto.getMessage()
        );

        // [2] Gemini API 규격(JSON)에 맞춰 요청 데이터 조립
        Map<String, Object> requestBody = createGeminiRequestBody(prompt);

        // [3] 통신 헤더 설정 (보내는 데이터가 JSON 형식임을 구글 서버에 알림)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // [4] 구글 서버로 API POST 요청 쏘고 응답받기
        String fullUrl = apiUrl + "?key=" + apiKey;
        Map<String, Object> response = restTemplate.postForObject(fullUrl, entity, Map.class);

        // [5] 구글이 준 복잡한 응답 데이터에서 순수 텍스트 답변만 받기
        String aiReply = extractReplyFromResponse(response);

        // 응답 DTO 그릇에 담아서 반환
        return new ChatResponseDto(aiReply);
    }

    // --- JSON 조립 및 해체용 헬퍼 메서드 ---

    private Map<String, Object> createGeminiRequestBody(String prompt) {
        Map<String, Object> part = new HashMap<>();
        part.put("text", prompt);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", Collections.singletonList(part));

        Map<String, Object> body = new HashMap<>();
        body.put("contents", Collections.singletonList(content));

        return body;
    }

    private String extractReplyFromResponse(Map<String, Object> response) {
        try {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
            return (String) parts.get(0).get("text");
        } catch (Exception e) {
            return "AI 응답을 처리하는 중 문제가 발생했습니다.";
        }
    }
}