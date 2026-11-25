package com.example.internshipjava2026korolchuk.dto;

import jakarta.validation.constraints.Email;

public record EmployeeUpdatingDto(
        String fullName,

        @Email
        String email,
        String position
) {
}
