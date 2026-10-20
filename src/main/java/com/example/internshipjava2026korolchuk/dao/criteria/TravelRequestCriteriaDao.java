package com.example.internshipjava2026korolchuk.dao.criteria;

import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.entity.TravelRequest_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class TravelRequestCriteriaDao {
    @PersistenceContext
    private EntityManager em;

    public long countActiveOnPeriodTravelRequests(LocalDate leftBorder, LocalDate rightBorder) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TravelRequest> root = cq.from(TravelRequest.class);

        Predicate afterStart = cb.greaterThanOrEqualTo(root.get(TravelRequest_.startDate), leftBorder);
        Predicate beforeEnd = cb.lessThanOrEqualTo(root.get(TravelRequest_.endDate), rightBorder);

        cq.select(cb.count(root)).where(cb.and(afterStart, beforeEnd));

        return em.createQuery(cq).getSingleResult();
    }
}
