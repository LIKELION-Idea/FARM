package com.farm.backend.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatResponseDto {
    // AI가 대답한 최종 친절한 답변
    private String reply;
}
