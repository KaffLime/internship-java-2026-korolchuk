package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.specifications.EmployeeFilter;
import com.example.internshipjava2026korolchuk.dao.specifications.EmployeeFilterSpecificationsDao;
import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeFilterSpecificationsDaoTest extends BaseIntegrationTest {
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
