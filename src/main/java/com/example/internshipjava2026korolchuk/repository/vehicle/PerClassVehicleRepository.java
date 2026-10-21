package com.example.internshipjava2026korolchuk.repository.vehicle;

import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassVehicle;
import com.example.internshipjava2026korolchuk.entity.vehicle.single.SingleVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerClassVehicleRepository extends JpaRepository<PerClassVehicle, Long> {
    List<PerClassVehicle> findByModel(String model);
}
