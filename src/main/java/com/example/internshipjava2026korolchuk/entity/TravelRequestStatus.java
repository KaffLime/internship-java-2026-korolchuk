package com.example.internshipjava2026korolchuk.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TravelRequestStatus {
    DRAFT,
    SUBMITTED,
    APPROVED,
    REJECTED,
    CANCELLED;

    @JsonValue
    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
