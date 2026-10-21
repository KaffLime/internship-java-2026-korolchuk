package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.jpql.TravelRequestSearchJpqlDao;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TravelRequestSearchJpqlDaoTest extends BaseIntegrationTest {
    @Autowired
    private TravelRequestSearchJpqlDao travelRequestSearchJpqlDao;

    @Test
    void shouldFindAllTravelRequestsByDepartmentId() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<TravelRequest> page = travelRequestSearchJpqlDao.findByDepartmentId(-1L, pageable);

        assertEquals(1, page.getTotalElements());
        assertTrue(page.getContent().stream().anyMatch(x -> x.getId().equals(-1L)));
    }
}
