package com.example.internshipjava2026korolchuk.dao.jpql;

import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TravelRequestSearchJpqlDao extends JpaRepository<TravelRequest, Long> {
    @Query("SELECT t FROM TravelRequest t WHERE t.department.id = :department_id")
    Page<TravelRequest> findByDepartmentId(@Param("department_id") Long departmentId, Pageable pageable);
}
