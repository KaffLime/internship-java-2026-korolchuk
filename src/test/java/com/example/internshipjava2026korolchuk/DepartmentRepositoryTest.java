package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

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
public class DepartmentRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void shouldUpdateDepartment() {
        Department newDep = new Department(-1L, "TEST", "00000");
        Department savedDep = departmentRepository.save(newDep);

        assertEquals(newDep.getName(), savedDep.getName());
        assertNotEquals("OGT", savedDep.getName());
        assertEquals("00000", savedDep.getCode());
    }

    @Test
    void shouldFindAllDepartments() {
        List<Department> depList = departmentRepository.findAll();

        assertEquals(2, depList.size());
        assertNotNull(depList.get(0));
        assertTrue(depList.stream().anyMatch(x -> x.getName().equals("OGT")));
    }

    @Test
    void shouldDeleteDepartment() {
        departmentRepository.deleteById(-1L);

        assertEquals(1, departmentRepository.count());
        assertTrue(departmentRepository.findById(-1L).isEmpty());
    }

    @Test
    void shouldNotFindDepartment() {
        Optional<Department> dep = departmentRepository.findById(-50L);

        assertTrue(dep.isEmpty());
    }
}
