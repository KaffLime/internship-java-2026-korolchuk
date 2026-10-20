package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.specifications.EmployeeFilter;
import com.example.internshipjava2026korolchuk.dao.specifications.EmployeeFilterSpecificationsDao;
import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class EmployeeFilterSpecificationsDaoTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private EmployeeFilterSpecificationsDao employeeFilterSpecificationsDao;

    @Test
    void shouldFilterEmployees() {
        Department dep = new Department();
        dep.setId(-1L);

        EmployeeFilter filter = EmployeeFilter.builder().position("Инженер").department(dep).build();
        Pageable pageable = PageRequest.of(0, 5);

        Page<Employee> empList = employeeFilterSpecificationsDao.findAll(EmployeeFilter.byFilter(filter), pageable);

        assertEquals(1, empList.getTotalElements());
        assertEquals("Корольчук Артем Сергеевич", empList.stream().findFirst().get().getFullName());
    }
}
