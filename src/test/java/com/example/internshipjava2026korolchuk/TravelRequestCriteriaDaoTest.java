package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.criteria.TravelRequestCriteriaDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TravelRequestCriteriaDao.class)
public class TravelRequestCriteriaDaoTest extends BaseIntegrationTest {
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
