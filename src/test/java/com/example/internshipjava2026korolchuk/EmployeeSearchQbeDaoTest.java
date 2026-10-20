package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.qbe.EmployeeSearchQbeDao;
import com.example.internshipjava2026korolchuk.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import(EmployeeSearchQbeDao.class)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class EmployeeSearchQbeDaoTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private EmployeeSearchQbeDao employeeSearchQbeDao;

    @Test
    void shouldFindEmployeesByExample() {
        Employee emp = new Employee();
        emp.setFullName("Иван");
        emp.setPosition("Бухгалтер");

        Pageable pageable = PageRequest.of(0, 5);

        Page<Employee> page = employeeSearchQbeDao.findByExample(emp, pageable);
        Employee foundEmp = page.getContent().get(0);

        assertNotNull(foundEmp);
        assertEquals(1, page.getTotalElements());
    }
}
