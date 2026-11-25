package com.example.internshipjava2026korolchuk.mapper;

import com.example.internshipjava2026korolchuk.dto.EmployeeCreationDto;
import com.example.internshipjava2026korolchuk.dto.EmployeeResponseDto;
import com.example.internshipjava2026korolchuk.dto.EmployeeUpdatingDto;
import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeResponseDto toDto(Employee employee);

    @Mapping(source = "departmentId", target = "department")
    Employee toEntity(EmployeeCreationDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEmployeeFromDto(EmployeeUpdatingDto dto, @MappingTarget Employee employee);

    default Department map(Long id) {
        if (id == null) {
            return null;
        }

        Department dep = new Department();
        dep.setId(id);

        return dep;
    }
}
