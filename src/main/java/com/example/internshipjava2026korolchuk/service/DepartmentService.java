package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.DepartmentDto;
import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Transactional(readOnly = true)
    public List<DepartmentDto> getAllDepartments() {
        ArrayList<Department> departments = (ArrayList<Department>) departmentRepository.findAll();
        ArrayList<DepartmentDto> departmentDtos = new ArrayList<>();

        for (Department department : departments) {
            departmentDtos.add(departmentMapper.toDto(department));
        }

        return departmentDtos;
    }

    @Transactional(readOnly = true)
    public DepartmentDto getDepartmentById(Long id) {
        return departmentMapper.toDto(departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Подразделения с таким id не существует")));
    }

    @Transactional
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        return departmentMapper.toDto(departmentRepository.save(departmentMapper.toEntity(departmentDto)));
    }

    @Transactional
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Подразделения с таким id не существует"));

        departmentMapper.updateDepartmentFromDto(departmentDto, department);

        return departmentMapper.toDto(departmentRepository.save(department));
    }

    @Transactional
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
