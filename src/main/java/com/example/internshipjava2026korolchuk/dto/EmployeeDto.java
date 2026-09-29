package com.example.internshipjava2026korolchuk.dto;

import com.example.internshipjava2026korolchuk.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    Long id;
    String fullName;
    String email;
    String position;
    Department department;
    LocalDate hireDate;
}
