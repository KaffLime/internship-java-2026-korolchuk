package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.repository.DepartmentRepository;
import com.example.internshipjava2026korolchuk.repository.EmployeeRepository;
import com.example.internshipjava2026korolchuk.repository.TravelRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class TravelRequestRepositoryTest {
    @Autowired
    private TravelRequestRepository travelRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private TravelRequest dbTr;
    private Employee dbEmp;
    private Department dbDep;

    @BeforeEach
    void init() {
        travelRequestRepository.deleteAll();
        employeeRepository.deleteAll();
        departmentRepository.deleteAll();

        Department dep = new Department(null, "OGT", "5125");
        dbDep = departmentRepository.save(dep);

        Employee emp = new Employee(null, "Artyom Korolchuk",
                "artem@mail.ru", "injener", dbDep, LocalDate.now());
        dbEmp = employeeRepository.save(emp);

        TravelRequest tr = new TravelRequest(null, dbEmp, dbDep, "Moscow", LocalDate.now(),
                LocalDate.now().plusDays(1), "Podpisat dogovor", TravelRequestStatus.DRAFT,
                null, null, new BigDecimal("100.00"));
        dbTr = travelRequestRepository.save(tr);
    }

    @Test
    void shouldSaveTravelRequest() {
        TravelRequest newTr = new TravelRequest(null, dbEmp, dbDep, "Piter", LocalDate.now(),
                LocalDate.now().plusDays(1), "Podpisat dogovor", TravelRequestStatus.DRAFT,
                null, null, new BigDecimal("100.00"));
        TravelRequest savedTr = travelRequestRepository.save(newTr);

        assertEquals(newTr.getDestination(), savedTr.getDestination());
        assertNotNull(savedTr.getId());
        assertEquals(2, travelRequestRepository.count());
    }

    @Test
    void shouldFindAllTravelRequests() {
        List<TravelRequest> trList = travelRequestRepository.findAll();

        assertEquals(1, trList.size());
        assertNotNull(trList.get(0));
    }

    @Test
    void shouldDeleteTravelRequest() {
        travelRequestRepository.delete(dbTr);

        assertEquals(0, travelRequestRepository.count());
    }

    @Test
    void shouldNotFindTravelRequest() {
        TravelRequest savedTr = travelRequestRepository.findAll().get(0);
        Optional<TravelRequest> tr = travelRequestRepository.findById(savedTr.getId() + 1);

        assertTrue(tr.isEmpty());
    }
}
