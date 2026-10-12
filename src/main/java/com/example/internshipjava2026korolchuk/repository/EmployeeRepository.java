package com.example.internshipjava2026korolchuk.repository;

import com.example.internshipjava2026korolchuk.entity.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @EntityGraph(attributePaths = {"department"})
    List<Employee> findAll();

    @EntityGraph(attributePaths = {"department"})
    Optional<Employee> findById(Long aLong);
}
