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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SingleVehicleService {
    private final SingleVehicleRepository singleVehicleRepository;
    private final SingleVehicleMapper singleVehicleMapper;

    @Transactional(readOnly = true)
    public List<SingleVehicleDto> getAllVehicle() {
        return singleVehicleRepository.findAll().stream().map(singleVehicleMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public SingleVehicleDto getVehicleById(@PathVariable Long id) {
        SingleVehicle singleVehicle = singleVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Single транспорта с таким id не существует")));

        return singleVehicleMapper.toDto(singleVehicle);
    }

    @Transactional(readOnly = true)
    public List<SingleVehicleDto> getVehicleByModel(@RequestParam String model) {
        ArrayList<SingleVehicle> singleVehicles = (ArrayList<SingleVehicle>) singleVehicleRepository.findByModel(model);

        return singleVehicles.stream().map(singleVehicleMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleCarDto> getAllCars() {
        return singleVehicleRepository.findAllCars().stream().map(singleVehicleMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SinglePlaneDto> getAllPlanes() {
        return singleVehicleRepository.findAllPlanes().stream().map(singleVehicleMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SingleTrainDto> getAllTrains() {
        return singleVehicleRepository.findAllTrains().stream().map(singleVehicleMapper::toDto).toList();
    }

    @Transactional
    public SingleCarDto createCar(@RequestBody SingleCarDto car) {
        return singleVehicleMapper.toDto(singleVehicleRepository.save(singleVehicleMapper.toEntity(car)));
    }

    @Transactional
    public SinglePlaneDto createPlane(@RequestBody SinglePlaneDto plane) {
        return singleVehicleMapper.toDto(singleVehicleRepository.save(singleVehicleMapper.toEntity(plane)));
    }

    @Transactional
    public SingleTrainDto createTrain(@RequestBody SingleTrainDto train) {
        return singleVehicleMapper.toDto(singleVehicleRepository.save(singleVehicleMapper.toEntity(train)));
    }

    @Transactional
    public SingleCarDto updateCar(@PathVariable Long id, @RequestBody SingleCarDto car) {
        SingleVehicle savedVehicle = singleVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Single транспорта с таким id не существует")));

        if (!(savedVehicle instanceof SingleCar savedCar)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в машину");
        }

        singleVehicleMapper.updateSingleCarFromDto(car, savedCar);

        return singleVehicleMapper.toDto(singleVehicleRepository.save(savedCar));
    }

    @Transactional
    public SinglePlaneDto updatePlane(@PathVariable Long id, @RequestBody SinglePlaneDto plane) {
        SingleVehicle savedVehicle = singleVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Single транспорта с таким id не существует")));

        if (!(savedVehicle instanceof SinglePlane savedPlane)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в самолет");
        }

        singleVehicleMapper.updateSinglePlaneFromDto(plane, savedPlane);

        return singleVehicleMapper.toDto(singleVehicleRepository.save(savedPlane));
    }

    @Transactional
    public SingleTrainDto updateTrain(@PathVariable Long id, @RequestBody SingleTrainDto train) {
        SingleVehicle savedVehicle = singleVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Single транспорта с таким id не существует")));

        if (!(savedVehicle instanceof SingleTrain savedTrain)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в поезд");
        }

        singleVehicleMapper.updateSingleTrainFromDto(train, savedTrain);

        return singleVehicleMapper.toDto(singleVehicleRepository.save(savedTrain));
    }

    @Transactional
    public void deleteVehicle(@PathVariable Long id) {
        singleVehicleRepository.deleteById(id);
    }
}
