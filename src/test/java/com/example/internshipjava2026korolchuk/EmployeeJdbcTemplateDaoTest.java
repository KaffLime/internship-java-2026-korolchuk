package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.jdbctemplate.EmployeeSearchJdbcTemplateDao;
import com.example.internshipjava2026korolchuk.dao.jdbctemplate.EmployeeSearchJdbcTemplateDaoImpl;
import com.example.internshipjava2026korolchuk.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JdbcTest
@Import(EmployeeSearchJdbcTemplateDaoImpl.class)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class EmployeeJdbcTemplateDaoTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private EmployeeSearchJdbcTemplateDao employeeSearchJdbcTemplateDao;

    @Test
    void shouldFindAllEmployeesInDepartment() {
        List<Employee> empList = employeeSearchJdbcTemplateDao.findByDepartmentId(-2L);

        assertEquals(2, empList.size());
        assertTrue(empList.stream().anyMatch(x -> x.getEmail().equals("ivan34@mail.ru")));
    }
}
