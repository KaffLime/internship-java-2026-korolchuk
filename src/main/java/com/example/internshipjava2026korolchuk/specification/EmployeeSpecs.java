package com.example.internshipjava2026korolchuk.specification;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Department_;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.entity.Employee_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EmployeeSpecs {

    public static Specification<Employee> containsFullName(String fullName) {
        return ((root, query, cb) -> {
            if (fullName == null) {
                return null;
            }

            String pattern = "%" + fullName + "%";
            return cb.like(cb.lower(root.get(Employee_.FULL_NAME)), cb.lower(cb.literal(pattern)));
        });
    }

    public static Specification<Employee> containsEmail(String email) {
        return ((root, query, cb) -> {
            if (email == null) {
                return null;
            }

            String pattern = "%" + email + "%";
            return cb.like(cb.lower(root.get(Employee_.EMAIL)), cb.lower(cb.literal(pattern)));
        });
    }

    public static Specification<Employee> containsPosition(String position) {
        return ((root, query, cb) -> {
            if (position == null) {
                return null;
            }

            String pattern = "%" + position + "%";
            return cb.like(cb.lower(root.get(Employee_.POSITION)), cb.lower(cb.literal(pattern)));
        });
    }

    public static Specification<Employee> inDepartment(Department dep) {
        return ((root, query, cb) -> {
            if (dep == null) {
                return null;
            }

            return cb.equal(root.get(Employee_.DEPARTMENT).get(Department_.ID), dep.getId());
        });
    }

    public static Specification<Employee> hiredBeforeDate(LocalDate date) {
        return ((root, query, cb) -> {
            if (date == null) {
                return null;
            }

            return cb.lessThanOrEqualTo(root.get(Employee_.HIRE_DATE), date);
        });
    }

    public static Specification<Employee> hiredAfterDate(LocalDate date) {
        return ((root, query, cb) -> {
            if (date == null) {
                return null;
            }

            return cb.greaterThanOrEqualTo(root.get(Employee_.HIRE_DATE), date);
        });
    }
}
