package com.example.internshipjava2026korolchuk.dao.criteria;

import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class TravelRequestCriteriaDao {
    @PersistenceContext
    private EntityManager em;

    public long countActiveTravelRequests() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TravelRequest> root = cq.from(TravelRequest.class);

        Predicate afterStart = cb.lessThanOrEqualTo(root.get("startDate"), LocalDateTime.now());
        Predicate beforeEnd = cb.greaterThanOrEqualTo(root.get("endDate"), LocalDateTime.now());

        cq.select(cb.count(root)).where(cb.and(afterStart, beforeEnd));

        return em.createQuery(cq).getSingleResult();
    }
}
