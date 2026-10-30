package com.example.internshipjava2026korolchuk.dto.vehicle;

public record PerClassPlaneDto(
        Long id,
        String model,
        Long maxSpeed,
        Long fuelCapacity,
        String planeType,
        Long massClass
) implements PerClassVehicleDto {
}
