package com.example.internshipjava2026korolchuk.repository;

import com.example.internshipjava2026korolchuk.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
