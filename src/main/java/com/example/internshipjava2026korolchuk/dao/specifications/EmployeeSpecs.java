package com.example.internshipjava2026korolchuk.dao.specifications;

import com.example.internshipjava2026korolchuk.entity.Department_;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.entity.Employee_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EmployeeSpecs {
    public static Specification<Employee> hiredBeforeDate(LocalDate date) {
        return ((root, query, cb) -> {
            if (date == null) {
                return null;
            }

            return cb.lessThan(root.get(Employee_.HIRE_DATE), date);
        });
    }

    public static Specification<Employee> hiredAfterDate(LocalDate date) {
        return ((root, query, cb) -> {
            if (date == null) {
                return null;
            }

            return cb.greaterThan(root.get(Employee_.HIRE_DATE), date);
        });
    }

    public static Specification<Employee> inDepartment(Long id) {
        return ((root, query, cb) -> {
            if (id == null) {
                return null;
            }

            return cb.equal(root.get(Employee_.DEPARTMENT).get(Department_.ID), id);
        });
    }

    public static Specification<Employee> hasPosition(String position) {
        return ((root, query, cb) -> {
            if (position == null) {
                return null;
            }

            String pattern = "%" + position + "%";
            return cb.like(root.get(Employee_.POSITION), pattern);
        });
    }
}
