package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.qbe.EmployeeSearchQbeDao;
import com.example.internshipjava2026korolchuk.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(EmployeeSearchQbeDao.class)
public class EmployeeSearchQbeDaoTest extends BaseIntegrationTest {
    @Autowired
    private EmployeeSearchQbeDao employeeSearchQbeDao;

    @Test
    void shouldFindEmployeesByExample() {
        Pageable pageable = PageRequest.of(0, 5);

        Page<Employee> page = employeeSearchQbeDao.findByExample("Иван", "ivan34@mail.ru", pageable);
        Employee foundEmp = page.getContent().get(0);

        assertNotNull(foundEmp);
        assertEquals(1, page.getTotalElements());
    }
}
