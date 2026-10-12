package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.jdbc.EmployeeSearchJdbcDao;
import com.example.internshipjava2026korolchuk.dao.jdbc.EmployeeSearchJdbcDaoImpl;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@JdbcTest
@Import(EmployeeSearchJdbcDaoImpl.class)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class EmployeeJdbcDaoTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private EmployeeSearchJdbcDao employeeSearchJdbcDao;

    @Test
    void shouldFindEmployee() {
        Optional<Employee> emp = employeeSearchJdbcDao.findById(-1L);

        assertFalse(emp.isEmpty());
    }
}
