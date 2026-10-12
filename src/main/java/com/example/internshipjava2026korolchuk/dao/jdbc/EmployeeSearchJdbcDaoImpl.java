package com.example.internshipjava2026korolchuk.dao.jdbc;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class EmployeeSearchJdbcDaoImpl implements EmployeeSearchJdbcDao {
    private final DataSource dataSource;

    public EmployeeSearchJdbcDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<Employee> findById(Long id) {
        Employee emp = null;
        Department dep = null;

        String sqlEmp = "SELECT * FROM employees WHERE id = ?";
        String sqlDep = "SELECT * FROM departments WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement psEmp = connection.prepareStatement(sqlEmp)) {

            psEmp.setLong(1, id);

            try (ResultSet rsEmp = psEmp.executeQuery()) {
                if (!rsEmp.next()) {
                    return Optional.empty();
                }

                emp = new Employee();
                emp.setId(rsEmp.getLong("id"));
                emp.setFullName(rsEmp.getString("full_name"));
                emp.setEmail(rsEmp.getString("email"));
                emp.setPosition(rsEmp.getString("position"));
                emp.setHireDate(rsEmp.getDate("hire_date").toLocalDate());

                long depId = rsEmp.getLong("department_id");

                try (PreparedStatement psDep = connection.prepareStatement(sqlDep)) {
                    psDep.setLong(1, depId);

                    try (ResultSet rsDep = psDep.executeQuery()) {
                        if (rsDep.next()) {
                            dep = new Department();
                            dep.setName(rsDep.getString("name"));
                            dep.setCode(rsDep.getString("code"));

                            emp.setDepartment(dep);
                        }
                    }
                }
            }

            return Optional.of(emp);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.ofNullable(emp);
    }
}
