package com.example.internshipjava2026korolchuk.repository;

import com.example.internshipjava2026korolchuk.entity.RequestHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestHistoryRepository extends JpaRepository<RequestHistory, Long> {

    @EntityGraph(attributePaths = {"travelRequest"})
    Page<RequestHistory> findAll(Specification<RequestHistory> spec, Pageable pageable);
}
