package com.example.internshipjava2026korolchuk.specification;

import com.example.internshipjava2026korolchuk.entity.RequestHistory;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.validator.DateRange;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@DateRange(startDate = "startChangedAt", endDate = "endChangedAt")
public class RequestHistoryFilter {
    TravelRequest travelRequest;
    TravelRequestStatus oldStatus;
    TravelRequestStatus newStatus;
    LocalDateTime startChangedAt;
    LocalDateTime endChangedAt;
    String comment;

    public static Specification<RequestHistory> byFilter(RequestHistoryFilter filter) {
        return Specification.allOf(
                RequestHistorySpecs.forTravelRequest(filter.travelRequest),
                RequestHistorySpecs.hasOldStatus(filter.getOldStatus()),
                RequestHistorySpecs.hasNewStatus(filter.getNewStatus()),
                RequestHistorySpecs.afterDate(filter.getStartChangedAt()),
                RequestHistorySpecs.beforeDate(filter.getEndChangedAt()),
                RequestHistorySpecs.hasComment(filter.getComment())
        );
    }
}
