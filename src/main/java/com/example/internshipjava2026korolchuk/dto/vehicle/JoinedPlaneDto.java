package com.example.internshipjava2026korolchuk.dto.vehicle;

public record JoinedPlaneDto(
        Long id,
        String model,
        Long maxSpeed,
        Long fuelCapacity,
        String planeType,
        Long massClass
) implements JoinedVehicleDto {
}
