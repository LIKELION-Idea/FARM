package com.farm.backend.farm;

import com.farm.backend.farm.dto.FarmProfileRequest;
import com.farm.backend.farm.dto.FarmProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farm-profile")
@RequiredArgsConstructor

public class FarmProfileController {

    private final FarmProfileService service;

    @PostMapping
    public ResponseEntity<FarmProfileResponse> create(
            @Valid @RequestBody FarmProfileRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmProfileResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
