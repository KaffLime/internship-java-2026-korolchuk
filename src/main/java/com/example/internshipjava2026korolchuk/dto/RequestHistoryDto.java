package com.example.internshipjava2026korolchuk.dto;

import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;

import java.time.LocalDateTime;

public record RequestHistoryDto(
        Long id,
        TravelRequest travelRequest,
        TravelRequestStatus oldStatus,
        TravelRequestStatus newStatus,
        LocalDateTime changedAt,
        String comment
) {
}
