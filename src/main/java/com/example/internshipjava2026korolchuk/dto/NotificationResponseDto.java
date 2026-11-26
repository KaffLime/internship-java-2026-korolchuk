package com.example.internshipjava2026korolchuk.dto;

import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;

public record NotificationResponseDto(
        String id,
        Long travelRequestId,
        TravelRequestStatus oldStatus,
        TravelRequestStatus newStatus
) {
}
