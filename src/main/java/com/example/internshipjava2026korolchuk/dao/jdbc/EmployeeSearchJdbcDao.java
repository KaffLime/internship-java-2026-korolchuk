package com.example.internshipjava2026korolchuk.dao.jdbc;

import com.example.internshipjava2026korolchuk.entity.Employee;

import java.util.Optional;

public interface EmployeeSearchJdbcDao {
    public Optional<Employee> findById(Long id);
}
