package com.example.internshipjava2026korolchuk.repository.vehicle;

import com.example.internshipjava2026korolchuk.entity.vehicle.single.SingleCar;
import com.example.internshipjava2026korolchuk.entity.vehicle.single.SinglePlane;
import com.example.internshipjava2026korolchuk.entity.vehicle.single.SingleTrain;
import com.example.internshipjava2026korolchuk.entity.vehicle.single.SingleVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SingleVehicleRepository extends JpaRepository<SingleVehicle, Long> {
    List<SingleVehicle> findByModel(String model);

    @Query("SELECT c FROM SingleCar c")
    List<SingleCar> findAllCars();

    @Query("SELECT p FROM SinglePlane p")
    List<SinglePlane> findAllPlanes();

    @Query("SELECT t FROM SingleTrain t")
    List<SingleTrain> findAllTrains();
}
