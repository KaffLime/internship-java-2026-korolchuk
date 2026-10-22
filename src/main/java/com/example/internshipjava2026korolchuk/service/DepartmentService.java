package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.DepartmentDto;
import com.example.internshipjava2026korolchuk.entity.Department;
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

    @Transactional(readOnly = true)
    public Page<DepartmentDto> getAllDepartments(DepartmentFilter filter, Pageable pageable) {
        Specification<Department> spec = DepartmentFilter.byFilter(filter);
        Page<Department> page = departmentRepository.findAll(spec, pageable);

        return page.map(departmentMapper::toDto);
    }

    @Transactional(readOnly = true)
    public DepartmentDto getDepartmentById(Long id) {
        return departmentMapper.toDto(departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Подразделение с id '" + id + "' не найдено")));
    }

    @Transactional
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        return departmentMapper.toDto(departmentRepository.save(departmentMapper.toEntity(departmentDto)));
    }

    @Transactional
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Подразделение с id '" + id + "' не найдено"));

        departmentMapper.updateDepartmentFromDto(departmentDto, department);

        return departmentMapper.toDto(departmentRepository.save(department));
    }

    @Transactional
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
