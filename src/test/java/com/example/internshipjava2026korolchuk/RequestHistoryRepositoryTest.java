package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.entity.RequestHistory;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.repository.RequestHistoryRepository;
import com.example.internshipjava2026korolchuk.repository.TravelRequestRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RequestHistoryRepositoryTest extends BaseIntegrationTest {
    @Autowired
    private RequestHistoryRepository requestHistoryRepository;

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void shouldSaveHistoryRecord() {
        TravelRequest travelRequest = travelRequestRepository.findById(-2L).get();

        RequestHistory requestHistory = new RequestHistory(
                -1L, travelRequest, TravelRequestStatus.SUBMITTED,
                TravelRequestStatus.REJECTED, null, "Заявка отклонена");

        RequestHistory savedRequestHistory = requestHistoryRepository.saveAndFlush(requestHistory);

        entityManager.refresh(savedRequestHistory);

        assertEquals(requestHistory.getComment(), savedRequestHistory.getComment());
        assertNotEquals(TravelRequestStatus.SUBMITTED, savedRequestHistory.getNewStatus());
        assertNotNull(savedRequestHistory.getChangedAt());
    }
}
