package com.example.internshipjava2026korolchuk.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DepartmentDto(
        Long id,

        @NotNull
        String name,

        @Size(min = 3, max = 10, message = "Длина кода должна составлять от 3 до 10 символов")
        String code
) {
}
