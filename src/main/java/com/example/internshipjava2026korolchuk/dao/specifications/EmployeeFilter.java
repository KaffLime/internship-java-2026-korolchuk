package com.example.internshipjava2026korolchuk.dao.specifications;

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
    String position;
    Department department;
    LocalDate startHireDate;
    LocalDate endHireDate;

    public static Specification<Employee> byFilter(EmployeeFilter filter) {
        return Specification.allOf(
                EmployeeSpecs.hiredAfterDate(filter.getStartHireDate()),
                EmployeeSpecs.hiredBeforeDate(filter.getEndHireDate()),
                EmployeeSpecs.inDepartment(filter.department.getId()),
                EmployeeSpecs.hasPosition(filter.getPosition())
        );
    }
}
