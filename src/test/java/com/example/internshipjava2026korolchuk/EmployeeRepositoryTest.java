package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.repository.DepartmentRepository;
import com.example.internshipjava2026korolchuk.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Employee dbEmp;
    private Department dbDep;

    @BeforeEach
    void init() {
        employeeRepository.deleteAll();
        departmentRepository.deleteAll();

        Department dep = new Department(null, "OGT", "5125");
        dbDep = departmentRepository.save(dep);

        Employee emp = new Employee(null, "Artyom Korolchuk",
                "artem@mail.ru", "injener", dbDep, LocalDate.now());
        dbEmp = employeeRepository.save(emp);
    }

    @Test
    void shouldSaveEmployee() {
        Employee newEmp = new Employee(null, "Egor Kartashov",
                "kart@mail.ru", "buhgalter", dbDep, LocalDate.now());
        Employee savedEmp = employeeRepository.save(newEmp);

        assertEquals(newEmp.getFullName(), savedEmp.getFullName());
        assertNotNull(savedEmp.getId());
        assertEquals(2, employeeRepository.count());
    }

    @Test
    void shouldFindAllEmployees() {
        List<Employee> empList = employeeRepository.findAll();

        assertEquals(1, empList.size());
        assertNotNull(empList.get(0));
    }

    @Test
    void shouldDeleteEmployee() {
        employeeRepository.delete(dbEmp);

        assertEquals(0, employeeRepository.count());
    }

    @Test
    void shouldNotFindEmployee() {
        Employee savedEmp = employeeRepository.findAll().get(0);
        Optional<Employee> emp = employeeRepository.findById(savedEmp.getId() + 1);

        assertTrue(emp.isEmpty());
    }
}
