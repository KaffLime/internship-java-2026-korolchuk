package com.example.internshipjava2026korolchuk.specification;

import com.example.internshipjava2026korolchuk.entity.RequestHistory;
import com.example.internshipjava2026korolchuk.entity.RequestHistory_;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.entity.TravelRequest_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class RequestHistorySpecs {

    public static Specification<RequestHistory> forTravelRequest(TravelRequest req) {
        return ((root, query, cb) -> {
            if (req == null) {
                return null;
            }

            return cb.equal(root.get(RequestHistory_.TRAVEL_REQUEST).get(TravelRequest_.ID), req.getId());
        });
    }

    public static Specification<RequestHistory> hasOldStatus(TravelRequestStatus status) {
        return ((root, query, cb) -> {
            if (status == null) {
                return null;
            }

            return cb.equal(root.get(RequestHistory_.OLD_STATUS), status);
        });
    }

    public static Specification<RequestHistory> hasNewStatus(TravelRequestStatus status) {
        return ((root, query, cb) -> {
            if (status == null) {
                return null;
            }

            return cb.equal(root.get(RequestHistory_.NEW_STATUS), status);
        });
    }

    public static Specification<RequestHistory> afterDate(LocalDateTime date) {
        return ((root, query, cb) -> {
            if (date == null) {
                return null;
            }

            return cb.greaterThanOrEqualTo(root.get(RequestHistory_.CHANGED_AT), date);
        });
    }

    public static Specification<RequestHistory> beforeDate(LocalDateTime date) {
        return ((root, query, cb) -> {
            if (date == null) {
                return null;
            }

            return cb.lessThanOrEqualTo(root.get(RequestHistory_.CHANGED_AT), date);
        });
    }

    public static Specification<RequestHistory> hasComment(String comment) {
        return ((root, query, cb) -> {
            if (comment == null) {
                return null;
            }

            return cb.equal(cb.lower(root.get(RequestHistory_.COMMENT)), cb.lower(cb.literal(comment)));
        });
    }
}
