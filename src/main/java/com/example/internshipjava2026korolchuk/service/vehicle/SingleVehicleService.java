package com.example.internshipjava2026korolchuk.service.vehicle;

import com.example.internshipjava2026korolchuk.dto.vehicle.SingleCarDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.SinglePlaneDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.SingleTrainDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.SingleVehicleDto;
import com.example.internshipjava2026korolchuk.entity.vehicle.single.SingleCar;
import com.example.internshipjava2026korolchuk.entity.vehicle.single.SinglePlane;
import com.example.internshipjava2026korolchuk.entity.vehicle.single.SingleTrain;
import com.example.internshipjava2026korolchuk.entity.vehicle.single.SingleVehicle;
import com.example.internshipjava2026korolchuk.mapper.vehicle.SingleVehicleMapper;
import com.example.internshipjava2026korolchuk.repository.vehicle.SingleVehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SingleVehicleService {
    private final SingleVehicleRepository singleVehicleRepository;

    private final SingleVehicleMapper singleVehicleMapper;

    @GetMapping
    public List<SingleVehicleDto> getAllVehicle() {
        ArrayList<SingleVehicle> vehicleList = (ArrayList<SingleVehicle>) singleVehicleRepository.findAll();

        ArrayList<SingleVehicleDto> vehicleDtoList = (ArrayList<SingleVehicleDto>) vehicleList
                .stream().map(singleVehicleMapper::toDto).toList();

        return vehicleDtoList;
    }

    @GetMapping("/{id}")
    public SingleVehicleDto getVehicleById(@PathVariable Long id) {
        SingleVehicle singleVehicle = singleVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Single транспорта с таким id не существует")));

        return singleVehicleMapper.toDto(singleVehicle);
    }

    @GetMapping("/search")
    public List<SingleVehicleDto> getVehicleByModel(@RequestParam String model) {
        ArrayList<SingleVehicle> singleVehicles = (ArrayList<SingleVehicle>) singleVehicleRepository.findByModel(model);

        return singleVehicles.stream().map(singleVehicleMapper::toDto).toList();
    }

    @PostMapping("/cars")
    public SingleCarDto createCar(@RequestBody SingleCarDto car) {
        return singleVehicleMapper.toDto(singleVehicleRepository.save(singleVehicleMapper.toEntity(car)));
    }

    @PostMapping("/planes")
    public SinglePlaneDto createPlane(@RequestBody SinglePlaneDto plane) {
        return singleVehicleMapper.toDto(singleVehicleRepository.save(singleVehicleMapper.toEntity(plane)));
    }

    @PostMapping("/trains")
    public SingleTrainDto createTrain(@RequestBody SingleTrainDto train) {
        return singleVehicleMapper.toDto(singleVehicleRepository.save(singleVehicleMapper.toEntity(train)));
    }

    @PatchMapping("/cars/{id}")
    public SingleCarDto updateCar(@PathVariable Long id, @RequestBody SingleCarDto car) {
        SingleVehicle savedVehicle = singleVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Single транспорта с таким id не существует")));

        if (!(savedVehicle instanceof SingleCar savedCar)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в машину");
        }

        singleVehicleMapper.updateSingleCarFromDto(car, savedCar);

        return singleVehicleMapper.toDto(singleVehicleRepository.save(savedCar));
    }

    @PatchMapping("/planes/{id}")
    public SinglePlaneDto updatePlane(@PathVariable Long id, @RequestBody SinglePlaneDto plane) {
        SingleVehicle savedVehicle = singleVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Single транспорта с таким id не существует")));

        if (!(savedVehicle instanceof SinglePlane savedPlane)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в самолет");
        }

        singleVehicleMapper.updateSinglePlaneFromDto(plane, savedPlane);

        return singleVehicleMapper.toDto(singleVehicleRepository.save(savedPlane));
    }

    @PatchMapping("/trains/{id}")
    public SingleTrainDto updateTrain(@PathVariable Long id, @RequestBody SingleTrainDto train) {
        SingleVehicle savedVehicle = singleVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Single транспорта с таким id не существует")));

        if (!(savedVehicle instanceof SingleTrain savedTrain)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в поезд");
        }

        singleVehicleMapper.updateSingleTrainFromDto(train, savedTrain);

        return singleVehicleMapper.toDto(singleVehicleRepository.save(savedTrain));
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        singleVehicleRepository.deleteById(id);
    }
}
