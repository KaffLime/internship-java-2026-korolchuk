package com.example.internshipjava2026korolchuk.dto.vehicle;

public record SingleCarDto(
        Long id,
        String model,
        Long maxSpeed,
        Long fuelCapacity,
        String licensePlate,
        String vin,
        Boolean used
) implements SingleVehicleDto {
}
