package com.example.internshipjava2026korolchuk.dao.nativequery;

import com.example.internshipjava2026korolchuk.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TravelRequestStatisticsNativeQueryDao extends JpaRepository<Department, Long> {
    @Query(value = """
            SELECT d.name, COUNT(t.id) AS request_count, SUM(t.estimated_cost) AS request_cost_sum
            FROM travel_requests t
            LEFT JOIN departments d ON t.department_id = d.id
            GROUP BY d.name""", nativeQuery = true)
    List<TravelRequestStatistics> receiveTravelRequestStatistics();
}
