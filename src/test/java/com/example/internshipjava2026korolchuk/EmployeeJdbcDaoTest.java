package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.jdbc.EmployeeSearchJdbcDao;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.repository.EmployeeRepository;
import com.example.internshipjava2026korolchuk.repository.TravelRequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(EmployeeSearchJdbcDao.class)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class EmployeeJdbcDaoTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private EmployeeSearchJdbcDao employeeSearchJdbcDao;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    @Test
    void shouldFindEmployee() {
        Optional<Employee> emp = employeeSearchJdbcDao.findById(-1L);

        assertFalse(emp.isEmpty());
    }

    @Test
    void shouldMoveEmployee() {
        boolean worked = employeeSearchJdbcDao.moveToDepartment(-3L, -1L);

        assertEquals(-1L, employeeRepository.findById(-3L).get().getDepartment().getId());
        assertEquals(-1L, travelRequestRepository.findById(-2L).get().getDepartment().getId());
        assertTrue(worked);
    }
}
