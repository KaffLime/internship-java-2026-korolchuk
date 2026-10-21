package com.example.internshipjava2026korolchuk.repository.vehicle;

import com.example.internshipjava2026korolchuk.entity.vehicle.single.SingleVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SingleVehicleRepository extends JpaRepository<SingleVehicle, Long> {
    List<SingleVehicle> findByModel(String model);
}
