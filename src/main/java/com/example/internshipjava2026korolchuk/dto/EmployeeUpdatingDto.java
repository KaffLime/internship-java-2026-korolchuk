package com.example.internshipjava2026korolchuk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record EmployeeUpdatingDto(
        String fullName,

        @Email
        String email,
        String position
) {
}
