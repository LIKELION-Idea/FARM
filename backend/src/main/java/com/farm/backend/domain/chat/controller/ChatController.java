package com.farm.backend.domain.chat.controller;

import com.farm.backend.domain.chat.dto.ChatRequestDto;
import com.farm.backend.domain.chat.dto.ChatResponseDto;
import com.farm.backend.domain.chat.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat") // 프론트엔드가 질문을 보낼 창구 주소
public class ChatController {

    private final GeminiService geminiService;

    // 프론트엔드에서 POST 방식으로 데이터를 보내면 메서드 실행
    @PostMapping
    public ResponseEntity<ChatResponseDto> chatWithAI(@RequestBody ChatRequestDto requestDto) {

        // GeminiService 에게 프론트엔드가 보낸 질문(requestDto)을 넘겨서 AI 답변을 받아옴.
        ChatResponseDto response = geminiService.getChatResponse(requestDto);

        // 상태 코드 200(정상 처리됨)과 함께 완성된 답변을 프론트엔드 화면으로 리턴.
        return ResponseEntity.ok(response);
    }
}