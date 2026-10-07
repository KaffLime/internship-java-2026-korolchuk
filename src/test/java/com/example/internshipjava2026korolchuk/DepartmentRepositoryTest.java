package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    private Department dbDep;

    @BeforeEach
    void init() {
        departmentRepository.deleteAll();

        Department dep = new Department(null, "OGT", "5125");
        dbDep = departmentRepository.save(dep);
    }

    @Test
    void shouldSaveEmployee() {
        Department newDep = new Department(null, "BUH", "3333");
        Department savedDep = departmentRepository.save(newDep);

        assertEquals(newDep.getName(), savedDep.getName());
        assertNotNull(savedDep.getId());
        assertEquals(2, departmentRepository.count());
    }

    @Test
    void shouldFindAllEmployees() {
        List<Department> depList = departmentRepository.findAll();

        assertEquals(1, depList.size());
        assertNotNull(depList.get(0));
    }

    @Test
    void shouldDeleteEmployee() {
        departmentRepository.delete(dbDep);

        assertEquals(0, departmentRepository.count());
    }

    @Test
    void shouldNotFindEmployee() {
        Department savedDep = departmentRepository.findAll().get(0);
        Optional<Department> dep = departmentRepository.findById(savedDep.getId() + 1);

        assertTrue(dep.isEmpty());
    }
}
