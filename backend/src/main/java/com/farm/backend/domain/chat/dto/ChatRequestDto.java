package com.farm.backend.domain.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRequestDto {
    // 어떤 농장의 질문인지 찾기 위한 농장 ID
    private Long farmProfileId;

    // 어르신이 실제로 물어본 질문 내용 (예: "사과 잎이 노랗게 변해")
    private String message;
}