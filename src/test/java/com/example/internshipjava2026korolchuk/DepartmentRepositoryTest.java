package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepartmentRepositoryTest extends BaseIntegrationTest {
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
    void shouldNotDeleteDepartment() {
        assertThrows(Exception.class, () -> departmentRepository.deleteById(-1L));
    }

    @Test
    void shouldNotFindDepartment() {
        Optional<Department> dep = departmentRepository.findById(-50L);

        assertTrue(dep.isEmpty());
    }
}
