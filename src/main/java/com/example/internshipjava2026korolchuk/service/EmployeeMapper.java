package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.EmployeeDto;
import com.example.internshipjava2026korolchuk.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {
    EmployeeDto toDto(Employee employee);

    Employee toEntity(EmployeeDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEmployeeFromDto(EmployeeDto dto, @MappingTarget Employee employee);
}
