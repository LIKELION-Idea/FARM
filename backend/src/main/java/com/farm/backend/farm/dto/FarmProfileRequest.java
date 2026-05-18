package com.farm.backend.farm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class FarmProfileRequest {
    @NotBlank(message = "지역은 필수입니다")
    @Size(max = 100)
    private String region;

    @NotBlank(message = "토양 유형은 필수입니다")
    @Size(max = 50)
    private String soilType;

    @NotBlank(message = "환경(노지/하우스/텃밭)은 필수입니다")
    @Size(max = 50)
    private String environment;

    @NotBlank(message = "배수 상태는 필수입니다")
    @Size(max = 20)
    private String drainage;

    @NotBlank(message = "영농 경력은 필수입니다")
    @Size(max = 10)
    private String level;
}
