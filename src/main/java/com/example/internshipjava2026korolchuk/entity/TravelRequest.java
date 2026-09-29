package com.example.internshipjava2026korolchuk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    Department department;
    String destination;
    LocalDate startDate;
    LocalDate endDate;
    String purpose;

    @Enumerated(EnumType.STRING)
    TravelRequestStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    BigDecimal estimatedCost;
}