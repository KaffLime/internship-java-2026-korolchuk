package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.EmployeeCreationDto;
import com.example.internshipjava2026korolchuk.dto.EmployeeResponseDto;
import com.example.internshipjava2026korolchuk.dto.EmployeeUpdatingDto;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.exception.NotValidDtoException;
import com.example.internshipjava2026korolchuk.mapper.EmployeeMapper;
import com.example.internshipjava2026korolchuk.repository.EmployeeRepository;
import com.example.internshipjava2026korolchuk.specification.EmployeeFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Page<EmployeeResponseDto> getAllEmployees(EmployeeFilter filter, Pageable pageable) {
        Specification<Employee> spec = EmployeeFilter.byFilter(filter);
        Page<Employee> page = employeeRepository.findAll(spec, pageable);

        return page.map(employeeMapper::toDto);
    }

    @Transactional(readOnly = true)
    public EmployeeResponseDto getEmployeeById(Long id) {
        return employeeMapper.toDto(employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id '" + id + "' не найден")));
    }

    @Transactional
    public EmployeeResponseDto createEmployee(EmployeeCreationDto dto) {
        if (dto.departmentId() == null) {
            throw new NotValidDtoException("Сотрудник не может существовать без отдела");
        }

        Employee emp = employeeRepository.saveAndFlush(employeeMapper.toEntity(dto));
        entityManager.refresh(emp);

        return employeeMapper.toDto(emp);
    }

    @Transactional
    public EmployeeResponseDto updateEmployee(Long id, EmployeeUpdatingDto dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id '" + id + "' не найден"));

        employeeMapper.updateEmployeeFromDto(dto, employee);

        Employee emp = employeeRepository.saveAndFlush(employee);
        entityManager.refresh(emp);

        return employeeMapper.toDto(emp);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional
    public long getEmployeesCountInDepartment(Long id) {
        return employeeRepository.countByDepartmentId(id);
    }
}
