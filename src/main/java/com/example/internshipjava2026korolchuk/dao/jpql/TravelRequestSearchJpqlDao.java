package com.example.internshipjava2026korolchuk.dao.jpql;

import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TravelRequestSearchJpqlDao extends JpaRepository<TravelRequest, Long> {
    @Query("SELECT t FROM TravelRequest t LEFT JOIN t.department d WHERE d.id = :department_id")
    List<TravelRequest> findByDepartmentId(@Param("department_id") Long departmentId);
}
