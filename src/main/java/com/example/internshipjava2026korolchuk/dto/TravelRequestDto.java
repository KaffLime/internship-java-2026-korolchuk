package com.example.internshipjava2026korolchuk.dto;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelRequestDto {
    Long id;
    Employee employee;
    Department department;
    String destination;
    LocalDate startDate;
    LocalDate endDate;
    String purpose;
    TravelRequestStatus status;
    BigDecimal estimatedCost;
}
