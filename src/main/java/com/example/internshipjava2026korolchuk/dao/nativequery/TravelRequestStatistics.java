package com.example.internshipjava2026korolchuk.dao.nativequery;

import java.math.BigDecimal;

public interface TravelRequestStatistics {
    String getName();

    Integer getRequestCount();

    BigDecimal getRequestCostSum();
}
