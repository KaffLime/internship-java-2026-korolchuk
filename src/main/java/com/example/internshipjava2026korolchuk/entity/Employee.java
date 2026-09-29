package com.example.internshipjava2026korolchuk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String fullName;
    String email;
    String position;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    Department department;
    LocalDate hireDate;
}
