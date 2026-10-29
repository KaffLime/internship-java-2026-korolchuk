package com.example.internshipjava2026korolchuk.mapper;

import com.example.internshipjava2026korolchuk.dto.DepartmentCreationDto;
import com.example.internshipjava2026korolchuk.dto.DepartmentResponseDto;
import com.example.internshipjava2026korolchuk.dto.DepartmentUpdatingDto;
import com.example.internshipjava2026korolchuk.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentResponseDto toDto(Department department);

    Department toEntity(DepartmentCreationDto dto);

    void updateDepartmentFromDto(DepartmentUpdatingDto dto, @MappingTarget Department department);
}
