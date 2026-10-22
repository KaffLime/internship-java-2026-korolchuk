package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.EmployeeDto;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.mapper.EmployeeMapper;
import com.example.internshipjava2026korolchuk.repository.EmployeeRepository;
import com.example.internshipjava2026korolchuk.specification.EmployeeFilter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Transactional(readOnly = true)
    public Page<EmployeeDto> getAllEmployees(EmployeeFilter filter, Pageable pageable) {
        Specification<Employee> spec = EmployeeFilter.byFilter(filter);
        Page<Employee> page = employeeRepository.findAll(spec, pageable);

        return page.map(employeeMapper::toDto);
    }

    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Long id) {
        return employeeMapper.toDto(employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id '" + id + "' не найден")));
    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEntity(employeeDto)));
    }

    @Transactional
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id '" + id + "' не найден"));

        employeeMapper.updateEmployeeFromDto(employeeDto, employee);

        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
