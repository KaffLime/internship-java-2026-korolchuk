package com.example.internshipjava2026korolchuk.dao.jdbctemplate;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeSearchJdbcTemplateDaoImpl implements EmployeeSearchJdbcTemplateDao {
    private final JdbcTemplate jdbc;

    public EmployeeSearchJdbcTemplateDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Employee> findByDepartmentId(Long id) {
        String sql = """
                SELECT e.id, e.full_name, e.email, e.position, e.hire_date, d.id AS dep_id, d.name, d.code
                FROM employees e LEFT JOIN departments d ON e.department_id = d.id
                WHERE d.id = ?""";

        return jdbc.query(sql, this::mapRow, id);
    }

    private Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee emp = new Employee();
        emp.setId(rs.getLong("id"));
        emp.setFullName(rs.getString("full_name"));
        emp.setEmail(rs.getString("email"));
        emp.setPosition(rs.getString("position"));
        emp.setHireDate(rs.getDate("hire_date").toLocalDate());

        Department dep = new Department();
        dep.setId(rs.getLong("dep_id"));
        dep.setName(rs.getString("name"));
        dep.setCode(rs.getString("code"));

        emp.setDepartment(dep);

        return emp;
    }
}
