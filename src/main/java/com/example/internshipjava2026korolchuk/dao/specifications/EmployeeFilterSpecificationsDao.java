package com.example.internshipjava2026korolchuk.dao.specifications;

import com.example.internshipjava2026korolchuk.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeFilterSpecificationsDao extends JpaRepository<Employee, Long>,
        JpaSpecificationExecutor<Employee> {
    @EntityGraph(attributePaths = {"department"})
    Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);
}
