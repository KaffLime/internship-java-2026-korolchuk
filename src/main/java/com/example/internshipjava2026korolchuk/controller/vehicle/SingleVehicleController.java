package com.example.internshipjava2026korolchuk.controller.vehicle;

import com.example.internshipjava2026korolchuk.dto.vehicle.SingleCarDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.SinglePlaneDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.SingleTrainDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.SingleVehicleDto;
import com.example.internshipjava2026korolchuk.service.vehicle.SingleVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/singleVehicles")
@RequiredArgsConstructor
public class SingleVehicleController {
    private final SingleVehicleService singleVehicleService;

    @GetMapping
    public List<SingleVehicleDto> getAllVehicle() {
        return singleVehicleService.getAllVehicle();
    }

    @GetMapping("/{id}")
    public SingleVehicleDto getVehicleById(@PathVariable Long id) {
        return singleVehicleService.getVehicleById(id);
    }

    @GetMapping("/search")
    public List<SingleVehicleDto> getVehicleByModel(@RequestParam String model) {
        return singleVehicleService.getVehicleByModel(model);
    }

    @GetMapping("/cars")
    public List<SingleCarDto> getAllCars() {
        return singleVehicleService.getAllCars();
    }

    @GetMapping("/planes")
    public List<SinglePlaneDto> getAllPlanes() {
        return singleVehicleService.getAllPlanes();
    }

    @GetMapping("/trains")
    public List<SingleTrainDto> getAllTrains() {
        return singleVehicleService.getAllTrains();
    }

    @PostMapping("/cars")
    public SingleCarDto createCar(@RequestBody SingleCarDto car) {
        return singleVehicleService.createCar(car);
    }

    @PostMapping("/planes")
    public SinglePlaneDto createPlane(@RequestBody SinglePlaneDto plane) {
        return singleVehicleService.createPlane(plane);
    }

    @PostMapping("/trains")
    public SingleTrainDto createTrain(@RequestBody SingleTrainDto train) {
        return singleVehicleService.createTrain(train);
    }

    @PatchMapping("/cars/{id}")
    public SingleCarDto updateCar(@PathVariable Long id, @RequestBody SingleCarDto car) {
        return singleVehicleService.updateCar(id, car);
    }

    @PatchMapping("/planes/{id}")
    public SinglePlaneDto updatePlane(@PathVariable Long id, @RequestBody SinglePlaneDto plane) {
        return singleVehicleService.updatePlane(id, plane);
    }

    @PatchMapping("/trains/{id}")
    public SingleTrainDto updateTrain(@PathVariable Long id, @RequestBody SingleTrainDto train) {
        return singleVehicleService.updateTrain(id, train);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        singleVehicleService.deleteVehicle(id);
    }
}
