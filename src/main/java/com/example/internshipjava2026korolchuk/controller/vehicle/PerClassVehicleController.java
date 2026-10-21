package com.example.internshipjava2026korolchuk.controller.vehicle;

import com.example.internshipjava2026korolchuk.dto.vehicle.PerClassCarDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.PerClassPlaneDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.PerClassTrainDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.PerClassVehicleDto;
import com.example.internshipjava2026korolchuk.service.vehicle.PerClassVehicleService;
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
@RequestMapping("/api/perClassVehicles")
@RequiredArgsConstructor
public class PerClassVehicleController {
    private final PerClassVehicleService perClassVehicleService;

    @GetMapping
    public List<PerClassVehicleDto> getAllVehicle() {
        return perClassVehicleService.getAllVehicle();
    }

    @GetMapping("/{id}")
    public PerClassVehicleDto getVehicleById(@PathVariable Long id) {
        return perClassVehicleService.getVehicleById(id);
    }

    @GetMapping("/search")
    public List<PerClassVehicleDto> getVehicleByModel(@RequestParam String model) {
        return perClassVehicleService.getVehicleByModel(model);
    }

    @PostMapping("/cars")
    public PerClassCarDto createCar(@RequestBody PerClassCarDto car) {
        return perClassVehicleService.createCar(car);
    }

    @PostMapping("/planes")
    public PerClassPlaneDto createPlane(@RequestBody PerClassPlaneDto plane) {
        return perClassVehicleService.createPlane(plane);
    }

    @PostMapping("/trains")
    public PerClassTrainDto createTrain(@RequestBody PerClassTrainDto train) {
        return perClassVehicleService.createTrain(train);
    }

    @PatchMapping("/cars/{id}")
    public PerClassCarDto updateCar(@PathVariable Long id, @RequestBody PerClassCarDto car) {
        return perClassVehicleService.updateCar(id, car);
    }

    @PatchMapping("/planes/{id}")
    public PerClassPlaneDto updatePlane(@PathVariable Long id, @RequestBody PerClassPlaneDto plane) {
        return perClassVehicleService.updatePlane(id, plane);
    }

    @PatchMapping("/trains/{id}")
    public PerClassTrainDto updateTrain(@PathVariable Long id, @RequestBody PerClassTrainDto train) {
        return perClassVehicleService.updateTrain(id, train);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        perClassVehicleService.deleteVehicle(id);
    }
}
