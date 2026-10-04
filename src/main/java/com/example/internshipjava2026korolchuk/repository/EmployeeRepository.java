package com.example.internshipjava2026korolchuk.repository;

import com.example.internshipjava2026korolchuk.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
