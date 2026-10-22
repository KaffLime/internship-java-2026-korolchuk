package com.example.internshipjava2026korolchuk.repository;

import com.example.internshipjava2026korolchuk.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Page<Department> findAll(Specification<Department> spec, Pageable pageable);
}
