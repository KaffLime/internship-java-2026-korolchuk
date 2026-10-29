package com.example.internshipjava2026korolchuk.service.vehicle;

import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedCarDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedPlaneDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedTrainDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.JoinedVehicleDto;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedCar;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedPlane;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedTrain;
import com.example.internshipjava2026korolchuk.entity.vehicle.joined.JoinedVehicle;
import com.example.internshipjava2026korolchuk.mapper.vehicle.JoinedVehicleMapper;
import com.example.internshipjava2026korolchuk.repository.vehicle.JoinedVehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinedVehicleService {

    private final JoinedVehicleRepository joinedVehicleRepository;
    private final JoinedVehicleMapper joinedVehicleMapper;

    @Transactional(readOnly = true)
    public List<JoinedVehicleDto> getAllVehicle() {
        return joinedVehicleRepository.findAll().stream().map(joinedVehicleMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public JoinedVehicleDto getVehicleById(@PathVariable Long id) {
        JoinedVehicle joinedVehicle = joinedVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Joined транспорта с таким id не существует")));

        return joinedVehicleMapper.toDto(joinedVehicle);
    }

    @Transactional(readOnly = true)
    public List<JoinedVehicleDto> getVehicleByModel(@RequestParam String model) {
        return joinedVehicleRepository.findByModel(model).stream().map(joinedVehicleMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<JoinedCarDto> getAllCars() {
        return joinedVehicleRepository.findAllCars().stream().map(joinedVehicleMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<JoinedPlaneDto> getAllPlanes() {
        return joinedVehicleRepository.findAllPlanes().stream().map(joinedVehicleMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<JoinedTrainDto> getAllTrains() {
        return joinedVehicleRepository.findAllTrains().stream().map(joinedVehicleMapper::toDto).toList();
    }

    @Transactional
    public JoinedCarDto createCar(@RequestBody JoinedCarDto car) {
        return joinedVehicleMapper.toDto(joinedVehicleRepository.save(joinedVehicleMapper.toEntity(car)));
    }

    @Transactional
    public JoinedPlaneDto createPlane(@RequestBody JoinedPlaneDto plane) {
        return joinedVehicleMapper.toDto(joinedVehicleRepository.save(joinedVehicleMapper.toEntity(plane)));
    }

    @Transactional
    public JoinedTrainDto createTrain(@RequestBody JoinedTrainDto train) {
        return joinedVehicleMapper.toDto(joinedVehicleRepository.save(joinedVehicleMapper.toEntity(train)));
    }

    @Transactional
    public JoinedCarDto updateCar(@PathVariable Long id, @RequestBody JoinedCarDto car) {
        JoinedVehicle savedVehicle = joinedVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Joined транспорта с таким id не существует")));

        if (!(savedVehicle instanceof JoinedCar savedCar)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в машину");
        }

        joinedVehicleMapper.updateJoinedCarFromDto(car, savedCar);

        return joinedVehicleMapper.toDto(joinedVehicleRepository.save(savedCar));
    }

    @Transactional
    public JoinedPlaneDto updatePlane(@PathVariable Long id, @RequestBody JoinedPlaneDto plane) {
        JoinedVehicle savedVehicle = joinedVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Joined транспорта с таким id не существует")));

        if (!(savedVehicle instanceof JoinedPlane savedPlane)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в самолет");
        }

        joinedVehicleMapper.updateJoinedPlaneFromDto(plane, savedPlane);

        return joinedVehicleMapper.toDto(joinedVehicleRepository.save(savedPlane));
    }

    @Transactional
    public JoinedTrainDto updateTrain(@PathVariable Long id, @RequestBody JoinedTrainDto train) {
        JoinedVehicle savedVehicle = joinedVehicleRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Joined транспорта с таким id не существует")));

        if (!(savedVehicle instanceof JoinedTrain savedTrain)) {
            throw new RuntimeException("Транспорт не удалось преобразовать в поезд");
        }

        joinedVehicleMapper.updateJoinedTrainFromDto(train, savedTrain);

        return joinedVehicleMapper.toDto(joinedVehicleRepository.save(savedTrain));
    }

    @Transactional
    public void deleteVehicle(@PathVariable Long id) {
        joinedVehicleRepository.deleteById(id);
    }
}
