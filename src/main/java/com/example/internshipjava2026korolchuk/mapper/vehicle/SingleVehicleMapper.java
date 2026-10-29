package com.example.internshipjava2026korolchuk.mapper.vehicle;

import com.example.internshipjava2026korolchuk.dto.vehicle.SingleCarDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.SinglePlaneDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.SingleTrainDto;
import com.example.internshipjava2026korolchuk.dto.vehicle.SingleVehicleDto;
import com.example.internshipjava2026korolchuk.entity.vehicle.single.SingleCar;
import com.example.internshipjava2026korolchuk.entity.vehicle.single.SinglePlane;
import com.example.internshipjava2026korolchuk.entity.vehicle.single.SingleTrain;
import com.example.internshipjava2026korolchuk.entity.vehicle.single.SingleVehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.SubclassExhaustiveStrategy;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION)
public interface SingleVehicleMapper {

    @SubclassMapping(source = SingleCar.class, target = SingleCarDto.class)
    @SubclassMapping(source = SinglePlane.class, target = SinglePlaneDto.class)
    @SubclassMapping(source = SingleTrain.class, target = SingleTrainDto.class)
    SingleVehicleDto toDto(SingleVehicle singleVehicle);

    @SubclassMapping(source = SingleCarDto.class, target = SingleCar.class)
    @SubclassMapping(source = SinglePlaneDto.class, target = SinglePlane.class)
    @SubclassMapping(source = SingleTrainDto.class, target = SingleTrain.class)
    SingleVehicle toEntity(SingleVehicleDto singleVehicleDto);

    SingleCarDto toDto(SingleCar singleCar);

    SinglePlaneDto toDto(SinglePlane singlePlane);

    SingleTrainDto toDto(SingleTrain singleTrain);

    SingleCar toEntity(SingleCarDto dto);

    SinglePlane toEntity(SinglePlaneDto dto);

    SingleTrain toEntity(SingleTrainDto dto);

    @Mapping(target = "id", ignore = true)
    void updateSingleCarFromDto(SingleCarDto dto, @MappingTarget SingleCar car);

    @Mapping(target = "id", ignore = true)
    void updateSinglePlaneFromDto(SinglePlaneDto dto, @MappingTarget SinglePlane plane);

    @Mapping(target = "id", ignore = true)
    void updateSingleTrainFromDto(SingleTrainDto dto, @MappingTarget SingleTrain train);
}
