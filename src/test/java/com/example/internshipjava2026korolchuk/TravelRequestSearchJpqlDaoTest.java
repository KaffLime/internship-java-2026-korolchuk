package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.jpql.TravelRequestSearchJpqlDao;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TravelRequestSearchJpqlDaoTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

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
