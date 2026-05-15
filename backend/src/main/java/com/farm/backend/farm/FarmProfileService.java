package com.farm.backend.farm;

import com.farm.backend.farm.dto.FarmProfileRequest;
import com.farm.backend.farm.dto.FarmProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class FarmProfileService {
    private final FarmProfileRepository repository;

    @Transactional
    public FarmProfileResponse create(FarmProfileRequest request) {
        FarmProfile farm = FarmProfile.builder()
                .region(request.getRegion())
                .soilType(request.getSoilType())
                .environment(request.getEnvironment())
                .drainage(request.getDrainage())
                .level(request.getLevel())
                .build();

        FarmProfile saved = repository.save(farm);
        return FarmProfileResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public FarmProfileResponse findById(Long id) {
        FarmProfile farm = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "농장 프로필을 찾을 수 없습니다. id=" + id));
        return FarmProfileResponse.from(farm);
    }
}
