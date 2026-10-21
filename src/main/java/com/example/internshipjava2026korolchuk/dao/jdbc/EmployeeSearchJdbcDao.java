package com.example.internshipjava2026korolchuk.dao.jdbc;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmployeeSearchJdbcDao {
    private final DataSource dataSource;

    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        Employee emp = null;

        String sql = """
                SELECT e.id AS empId, e.full_name, e.email, e.position, e.hire_date,
                d.id AS depId, d.name, d.code
                FROM employees e
                LEFT JOIN departments d ON e.department_id = d.id
                WHERE e.id = ?""";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement psEmp = connection.prepareStatement(sql)) {

            psEmp.setLong(1, id);

            try (ResultSet rsEmp = psEmp.executeQuery()) {
                if (!rsEmp.next()) {
                    return Optional.empty();
                }

                emp = new Employee();
                emp.setId(rsEmp.getLong("empId"));
                emp.setFullName(rsEmp.getString("full_name"));
                emp.setEmail(rsEmp.getString("email"));
                emp.setPosition(rsEmp.getString("position"));
                emp.setHireDate(rsEmp.getDate("hire_date").toLocalDate());

                Department dep = new Department();

                dep.setId(rsEmp.getLong("depId"));
                dep.setName(rsEmp.getString("name"));
                dep.setCode(rsEmp.getString("code"));

                emp.setDepartment(dep);
            }

            return Optional.of(emp);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.ofNullable(emp);
    }

    public boolean moveToDepartment(Long empId, Long newDepId) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            String getEmpQuery = "SELECT * FROM employees WHERE id = ?";
            String updateEmpQuery = "UPDATE employees SET department_id = ? WHERE id = ?";
            String updateTravelReqsQuery = """
                    UPDATE travel_requests SET department_id = ?
                    WHERE employee_id = ? AND status = 'DRAFT'""";

            Employee emp = new Employee();
            long curDepId;

            try (PreparedStatement getEmpPs = connection.prepareStatement(getEmpQuery);
                 PreparedStatement updateEmpPs = connection.prepareStatement(updateEmpQuery);
                 PreparedStatement updateTravelReqsPs = connection.prepareStatement(updateTravelReqsQuery)) {

                getEmpPs.setLong(1, empId);

                try (ResultSet rsEmp = getEmpPs.executeQuery()) {
                    if (!rsEmp.next())
                        return false;

                    curDepId = rsEmp.getLong("department_id");

                    if (curDepId == newDepId) {
                        return false;
                    }

                    emp.setId(empId);
                    emp.setFullName(rsEmp.getString("full_name"));
                    emp.setEmail(rsEmp.getString("email"));
                    emp.setPosition(rsEmp.getString("position"));
                    emp.setHireDate(rsEmp.getDate("hire_date").toLocalDate());
                }

                updateEmpPs.setLong(1, newDepId);
                updateEmpPs.setLong(2, empId);

                long movedEmpsCount = updateEmpPs.executeUpdate();

                if (movedEmpsCount == 0)
                    return false;

                updateTravelReqsPs.setLong(1, newDepId);
                updateTravelReqsPs.setLong(2, empId);

                updateTravelReqsPs.executeUpdate();

                connection.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return true;
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
