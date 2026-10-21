package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.jdbc.EmployeeSearchJdbcDao;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.repository.EmployeeRepository;
import com.example.internshipjava2026korolchuk.repository.TravelRequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(EmployeeSearchJdbcDao.class)
public class EmployeeJdbcDaoTest extends BaseIntegrationTest {
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
