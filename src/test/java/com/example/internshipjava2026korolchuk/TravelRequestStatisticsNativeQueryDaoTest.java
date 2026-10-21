package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.nativequery.TravelRequestStatistics;
import com.example.internshipjava2026korolchuk.dao.nativequery.TravelRequestStatisticsNativeQueryDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelRequestStatisticsNativeQueryDaoTest extends BaseIntegrationTest {
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
