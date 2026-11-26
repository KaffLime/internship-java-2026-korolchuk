package com.example.internshipjava2026korolchuk.dto;

import com.example.internshipjava2026korolchuk.entity.TransferChannel;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;

import java.time.LocalDateTime;

public record NotificationRequestDto(
        Long travelRequestId,
        TravelRequestStatus oldStatus,
        TravelRequestStatus newStatus,
        LocalDateTime notificationSentAt,
        TransferChannel channel
) {
}
