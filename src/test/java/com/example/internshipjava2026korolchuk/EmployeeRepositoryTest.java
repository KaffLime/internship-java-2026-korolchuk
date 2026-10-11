package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.repository.DepartmentRepository;
import com.example.internshipjava2026korolchuk.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class EmployeeRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void shouldUpdateEmployee() {
        Department dep = departmentRepository.findById(-2L).get();

        Employee newEmp = new Employee(-1L, "Карташов Егор Акакьевич",
                "kart@mail.ru", "Бухгалтер", dep, LocalDate.now());
        Employee savedEmp = employeeRepository.save(newEmp);

        assertEquals(newEmp.getFullName(), savedEmp.getFullName());
        assertNotEquals("artem@mail.ru", savedEmp.getEmail());
        assertEquals("Бухгалтер", savedEmp.getPosition());
    }

    @Test
    void shouldFindAllEmployees() {
        List<Employee> empList = employeeRepository.findAll();

        assertEquals(3, empList.size());
        assertNotNull(empList.get(0));
        assertTrue(empList.stream().anyMatch(x -> x.getEmail().equals("artem@mail.ru")));
    }

    @Test
    void shouldDeleteEmployee() {
        employeeRepository.deleteById(-1L);

        assertEquals(2, employeeRepository.count());
        assertTrue(employeeRepository.findById(-1L).isEmpty());
    }

    @Test
    void shouldNotFindEmployee() {
        Optional<Employee> emp = employeeRepository.findById(-50L);

        assertTrue(emp.isEmpty());
    }
}
