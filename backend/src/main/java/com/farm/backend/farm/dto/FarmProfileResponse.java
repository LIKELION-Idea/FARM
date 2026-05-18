package com.farm.backend.farm.dto;

import com.farm.backend.farm.FarmProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor

public class FarmProfileResponse {
    private Long id;
    private String region;
    private String soilType;
    private String environment;
    private String drainage;
    private String level;
    private LocalDateTime createdAt;

    public static FarmProfileResponse from(FarmProfile farm) {
        return FarmProfileResponse.builder()
                .id(farm.getId())
                .region(farm.getRegion())
                .soilType(farm.getSoilType())
                .environment(farm.getEnvironment())
                .drainage(farm.getDrainage())
                .level(farm.getLevel())
                .createdAt(farm.getCreatedAt())
                .build();
    }
}
