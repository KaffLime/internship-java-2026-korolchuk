package com.example.internshipjava2026korolchuk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmployeeCreationDto(
        @NotNull
        String fullName,

        @Email
        String email,
        String position,

        @NotNull
        Long departmentId,
        LocalDate hireDate
) {
}
