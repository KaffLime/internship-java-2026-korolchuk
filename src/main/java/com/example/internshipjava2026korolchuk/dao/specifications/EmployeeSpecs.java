package com.example.internshipjava2026korolchuk.dao.specifications;

import com.example.internshipjava2026korolchuk.entity.Employee;
import org.springframework.data.jpa.domain.PredicateSpecification;

import java.time.LocalDate;

public class EmployeeSpecs {
    public static PredicateSpecification<Employee> hasWorkedForYear() {
        return (from, builder) -> {
            LocalDate date = LocalDate.now().minusYears(1);
            return builder.lessThan(from.get("hireDate"), date);
        };
    }

    public static PredicateSpecification<Employee> emailLike(String condition) {
        return ((from, builder) -> {
            return builder.like(from.get("email"), condition);
        });
    }
}
