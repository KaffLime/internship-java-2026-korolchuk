package com.example.internshipjava2026korolchuk.repository;

import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelRequestRepository extends JpaRepository<TravelRequest, Long> {

    @EntityGraph(attributePaths = {"department", "employee"})
    Page<TravelRequest> findAll(Specification<TravelRequest> spec, Pageable pageable);

    @EntityGraph(attributePaths = {"department", "employee"})
    Optional<TravelRequest> findById(Long id);
}
