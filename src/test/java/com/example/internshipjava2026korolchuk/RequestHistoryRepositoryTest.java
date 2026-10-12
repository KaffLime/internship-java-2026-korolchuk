package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.entity.RequestHistory;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.repository.RequestHistoryRepository;
import com.example.internshipjava2026korolchuk.repository.TravelRequestRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class RequestHistoryRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private RequestHistoryRepository requestHistoryRepository;

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
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
