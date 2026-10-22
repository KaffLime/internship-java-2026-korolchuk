package com.example.internshipjava2026korolchuk.specification;

import com.example.internshipjava2026korolchuk.entity.Department_;
import com.example.internshipjava2026korolchuk.entity.Employee_;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.entity.TravelRequest_;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TravelRequestSpecs {

    public static Specification<TravelRequest> forEmployee(Long id) {
        return ((root, query, cb) -> {
            if (id == null) {
                return null;
            }

            return cb.equal(root.get(TravelRequest_.EMPLOYEE).get(Employee_.ID), id);
        });
    }

    public static Specification<TravelRequest> inDepartment(Long id) {
        return ((root, query, cb) -> {
            if (id == null) {
                return null;
            }

            return cb.equal(root.get(TravelRequest_.DEPARTMENT).get(Department_.ID), id);
        });
    }

    public static Specification<TravelRequest> hasDestination(String destination) {
        return ((root, query, cb) -> {
            if (destination == null) {
                return null;
            }

            return cb.equal(root.get(TravelRequest_.DESTINATION), destination);
        });
    }

    public static Specification<TravelRequest> startingAfterDate(LocalDate date) {
        return ((root, query, cb) -> {
            if (date == null) {
                return null;
            }

            return cb.greaterThanOrEqualTo(root.get(TravelRequest_.START_DATE), date);
        });
    }

    public static Specification<TravelRequest> startingBeforeDate(LocalDate date) {
        return ((root, query, cb) -> {
            if (date == null) {
                return null;
            }

            return cb.lessThanOrEqualTo(root.get(TravelRequest_.START_DATE), date);
        });
    }

    public static Specification<TravelRequest> endingAfterDate(LocalDate date) {
        return ((root, query, cb) -> {
            if (date == null) {
                return null;
            }

            return cb.greaterThanOrEqualTo(root.get(TravelRequest_.END_DATE), date);
        });
    }

    public static Specification<TravelRequest> endingBeforeDate(LocalDate date) {
        return ((root, query, cb) -> {
            if (date == null) {
                return null;
            }

            return cb.lessThanOrEqualTo(root.get(TravelRequest_.END_DATE), date);
        });
    }

    public static Specification<TravelRequest> containsPurpose(String purpose) {
        return ((root, query, cb) -> {
            if (purpose == null) {
                return null;
            }

            String pattern = "%" + purpose + "%";
            return cb.like(root.get(TravelRequest_.PURPOSE), pattern);
        });
    }

    public static Specification<TravelRequest> hasStatus(TravelRequestStatus status) {
        return ((root, query, cb) -> {
            if (status == null) {
                return null;
            }

            return cb.equal(root.get(TravelRequest_.STATUS), status);
        });
    }

    public static Specification<TravelRequest> higherThanCost(BigDecimal minEstimatedCost) {
        return ((root, query, cb) -> {
            if (minEstimatedCost == null) {
                return null;
            }

            return cb.greaterThanOrEqualTo(root.get(TravelRequest_.ESTIMATED_COST), minEstimatedCost);
        });
    }

    public static Specification<TravelRequest> lowerThanCost(BigDecimal maxEstimatedCost) {
        return ((root, query, cb) -> {
            if (maxEstimatedCost == null) {
                return null;
            }

            return cb.lessThanOrEqualTo(root.get(TravelRequest_.ESTIMATED_COST), maxEstimatedCost);
        });
    }
}
