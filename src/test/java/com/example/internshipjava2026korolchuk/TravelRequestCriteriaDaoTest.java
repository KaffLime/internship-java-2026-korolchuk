package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.criteria.TravelRequestCriteriaDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(TravelRequestCriteriaDao.class)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TravelRequestCriteriaDaoTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private TravelRequestCriteriaDao travelRequestCriteriaDao;

    @Test
    void shouldCountActiveTravelRequests() {
        long count = travelRequestCriteriaDao.countActiveOnPeriodTravelRequests(
                LocalDate.of(2026, Month.JANUARY, 1),
                LocalDate.now()
        );

        assertEquals(2, count);
    }
}
