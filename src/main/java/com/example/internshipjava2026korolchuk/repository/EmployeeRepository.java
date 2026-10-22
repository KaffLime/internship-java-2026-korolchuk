package com.example.internshipjava2026korolchuk.repository;

import com.example.internshipjava2026korolchuk.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @EntityGraph(attributePaths = {"department"})
    Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);

    @Query(value = "SELECT * FROM employees", nativeQuery = true)
    List<Employee> findAllWithDepartment();

    @EntityGraph(attributePaths = {"department"})
    Optional<Employee> findById(Long id);
}
