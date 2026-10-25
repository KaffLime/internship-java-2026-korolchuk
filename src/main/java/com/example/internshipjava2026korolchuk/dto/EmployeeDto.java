package com.example.internshipjava2026korolchuk.dto;

import com.example.internshipjava2026korolchuk.entity.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmployeeDto(
        Long id,

        @NotNull
        String fullName,

        @Email
        String email,
        String position,
        Department department,
        LocalDate hireDate
) {
}
