package com.example.internshipjava2026korolchuk.dao.qbe;

import com.example.internshipjava2026korolchuk.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface EmployeeSearchQbeDao extends JpaRepository<Employee, Long>, QueryByExampleExecutor<Employee> {
}
