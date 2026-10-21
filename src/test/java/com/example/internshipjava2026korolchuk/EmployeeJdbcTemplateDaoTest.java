package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.jdbctemplate.EmployeeSearchJdbcTemplateDao;
import com.example.internshipjava2026korolchuk.dao.jdbctemplate.EmployeeSearchJdbcTemplateDaoImpl;
import com.example.internshipjava2026korolchuk.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(EmployeeSearchJdbcTemplateDaoImpl.class)
public class EmployeeJdbcTemplateDaoTest extends BaseIntegrationTest {
    @Autowired
    private EmployeeSearchJdbcTemplateDao employeeSearchJdbcTemplateDao;

    @Test
    void shouldFindAllEmployeesInDepartment() {
        List<Employee> empList = employeeSearchJdbcTemplateDao.findByDepartmentId(-2L);

        assertEquals(2, empList.size());
        assertTrue(empList.stream().anyMatch(x -> x.getEmail().equals("ivan34@mail.ru")));
    }
}
