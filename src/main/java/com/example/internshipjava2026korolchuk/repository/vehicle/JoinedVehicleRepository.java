package com.example.internshipjava2026korolchuk.repository.vehicle;

import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedVehicle;
import com.example.internshipjava2026korolchuk.entity.vehicle.per_class.PerClassVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoinedVehicleRepository extends JpaRepository<JoinedVehicle, Long> {
    List<JoinedVehicle> findByModel(String model);
}
