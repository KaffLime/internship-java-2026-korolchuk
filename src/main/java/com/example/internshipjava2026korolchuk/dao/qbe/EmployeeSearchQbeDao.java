package com.example.internshipjava2026korolchuk.dao.qbe;

import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.entity.Employee_;
import com.example.internshipjava2026korolchuk.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class EmployeeSearchQbeDao {
    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public Page<Employee> findByExample(Employee emp, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths(Employee_.ID)
                .withMatcher(Employee_.EMAIL, match -> match.ignoreCase())
                .withMatcher(Employee_.FULL_NAME, match -> match.ignoreCase().contains())
                .withMatcher(Employee_.POSITION, match -> match.ignoreCase().contains());

        return employeeRepository.findAll(Example.of(emp, matcher), pageable);
    }
}
