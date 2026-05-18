package com.farm.backend.farm;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "farm_profile")
@EntityListeners(AuditingEntityListener.class)   // ← 추가
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FarmProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String region;

    @Column(name = "soil_type", nullable = false, length = 50)
    private String soilType;

    @Column(nullable = false, length = 50)
    private String environment;

    @Column(nullable = false, length = 20)
    private String drainage;

    @Column(nullable = false, length = 10)
    private String level;

    @CreatedDate                                    // ← 변경
    @Column(name = "created_at", updatable = false) // ← insertable = false 제거
    private LocalDateTime createdAt;
}