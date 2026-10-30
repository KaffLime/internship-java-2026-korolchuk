package com.example.internshipjava2026korolchuk.dto;

import java.time.LocalDate;

public record EmployeeResponseDto(
        Long id,
        String fullName,
        String email,
        String position,
        ShortDepartmentResponseDto department,
        LocalDate hireDate
) {
}
