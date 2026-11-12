package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.DepartmentCreationDto;
import com.example.internshipjava2026korolchuk.dto.DepartmentResponseDto;
import com.example.internshipjava2026korolchuk.dto.DepartmentUpdatingDto;
import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.exception.CannotDeleteEntityException;
import com.example.internshipjava2026korolchuk.mapper.DepartmentMapper;
import com.example.internshipjava2026korolchuk.repository.DepartmentRepository;
import com.example.internshipjava2026korolchuk.specification.DepartmentFilter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final EmployeeService employeeService;

    @Transactional(readOnly = true)
    public Page<DepartmentResponseDto> getAllDepartments(DepartmentFilter filter, Pageable pageable) {
        Specification<Department> spec = DepartmentFilter.byFilter(filter);
        Page<Department> page = departmentRepository.findAll(spec, pageable);

        return page.map(departmentMapper::toDto);
    }

    @Transactional(readOnly = true)
    public DepartmentResponseDto getDepartmentById(Long id) {
        return departmentMapper.toDto(departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Подразделение с id '" + id + "' не найдено")));
    }

    @Transactional
    public DepartmentResponseDto createDepartment(DepartmentCreationDto dto) {
        return departmentMapper.toDto(departmentRepository.save(departmentMapper.toEntity(dto)));
    }

    @Transactional
    public DepartmentResponseDto updateDepartment(Long id, DepartmentUpdatingDto dto) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Подразделение с id '" + id + "' не найдено"));

        departmentMapper.updateDepartmentFromDto(dto, department);

        return departmentMapper.toDto(departmentRepository.save(department));
    }

    @Transactional
    public void deleteDepartment(Long id) {
        if (employeeService.getEmployeesCountInDepartment(id) > 0) {
            throw new CannotDeleteEntityException("Удалить подразделение с id '"
                    + id + "' невозможно, в нем находятся сотрудники");
        }

        departmentRepository.deleteById(id);
    }
}
