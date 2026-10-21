package com.example.internshipjava2026korolchuk.dao.jdbctemplate;

import com.example.internshipjava2026korolchuk.entity.Employee;

import java.util.List;

public interface EmployeeSearchJdbcTemplateDao {
    List<Employee> findByDepartmentId(Long id);
}
