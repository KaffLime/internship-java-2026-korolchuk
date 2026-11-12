package com.example.internshipjava2026korolchuk.dto;

import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.validator.DateRange;

import java.math.BigDecimal;
import java.time.LocalDate;

@DateRange
public record TravelRequestResponseDto(
        Long id,
        ShortEmployeeResponseDto employee,
        ShortDepartmentResponseDto department,
        String destination,
        LocalDate startDate,
        LocalDate endDate,
        String purpose,
        TravelRequestStatus status,
        BigDecimal estimatedCost
) {
}
