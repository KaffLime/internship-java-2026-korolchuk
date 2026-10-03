package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.DepartmentDto;
import com.example.internshipjava2026korolchuk.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);

    Department toEntity(DepartmentDto dto);

    @Mapping(target = "id", ignore = true)
    void updateDepartmentFromDto(DepartmentDto dto, @MappingTarget Department department);
}
