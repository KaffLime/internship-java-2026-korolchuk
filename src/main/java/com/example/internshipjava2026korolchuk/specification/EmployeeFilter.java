package com.example.internshipjava2026korolchuk.specification;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class EmployeeFilter {
    String fullName;
    String email;
    String position;
    Department department;
    LocalDate afterHireDate;
    LocalDate beforeHireDate;

    public static Specification<Employee> byFilter(EmployeeFilter filter) {
        return Specification.allOf(
                EmployeeSpecs.containsFullName(filter.getFullName()),
                EmployeeSpecs.containsEmail(filter.getEmail()),
                EmployeeSpecs.containsPosition(filter.getPosition()),
                EmployeeSpecs.inDepartment(filter.department),
                EmployeeSpecs.hiredAfterDate(filter.getAfterHireDate()),
                EmployeeSpecs.hiredBeforeDate(filter.getBeforeHireDate())
        );
    }
}
