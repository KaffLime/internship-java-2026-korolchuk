package com.example.internshipjava2026korolchuk.dto;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.validator.DateRange;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@DateRange
public record TravelRequestDto(
        @Null
        Long id,
        Employee employee,
        Department department,
        String destination,
        LocalDate startDate,
        LocalDate endDate,

        @Size(min = 10, max = 500, message = "Длина назначения должна составлять от 10 до 500 символов")
        String purpose,
        TravelRequestStatus status,

        @DecimalMin(value = "0.0", message = "Стоимость не может быть отрицательной")
        BigDecimal estimatedCost
) {
}
