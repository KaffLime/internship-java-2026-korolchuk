package com.example.internshipjava2026korolchuk.repository;

import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TravelRequestRepository extends JpaRepository<TravelRequest, Long> {
    @EntityGraph(attributePaths = {"department", "employee"})
    List<TravelRequest> findAll();

    @EntityGraph(attributePaths = {"department", "employee"})
    Optional<TravelRequest> findById(Long aLong);
}
