package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.nativequery.TravelRequestStatistics;
import com.example.internshipjava2026korolchuk.dao.nativequery.TravelRequestStatisticsNativeQueryDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TravelRequestStatisticsNativeQueryDaoTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private TravelRequestStatisticsNativeQueryDao travelRequestStatisticsNativeQueryDao;

    @Test
    void shouldReceiveRequestsStatisticsGroupedByDepartment() {
        List<TravelRequestStatistics> statistics = travelRequestStatisticsNativeQueryDao.receiveTravelRequestStatistics();

        assertEquals(2, statistics.size());
        assertEquals(1, statistics.stream()
                .filter(x -> x.getName().equals("BUH")).findFirst().get().getRequestCount());
    }
}
