package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.EmployeeDto;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployees() {
        ArrayList<Employee> employees = (ArrayList<Employee>) employeeRepository.findAll();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Employee employee : employees) {
            employeeDtos.add(employeeMapper.toDto(employee));
        }

        return employeeDtos;
    }

    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Long id) {
        return employeeMapper.toDto(employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователя с таким id не существует")));
    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEntity(employeeDto)));
    }

    @Transactional
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователя с таким id не существует"));

        employeeMapper.updateEmployeeFromDto(employeeDto, employee);

        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
