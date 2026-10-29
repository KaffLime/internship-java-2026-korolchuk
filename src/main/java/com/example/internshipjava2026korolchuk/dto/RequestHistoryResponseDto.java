package com.example.internshipjava2026korolchuk.dto;

import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;

public record RequestHistoryResponseDto(
        Long id,
        Long travelRequestId,
        TravelRequestStatus oldStatus,
        TravelRequestStatus newStatus,
        String comment
) {
}
