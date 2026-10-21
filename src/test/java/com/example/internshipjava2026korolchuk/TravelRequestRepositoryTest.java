package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dao.criteria.TravelRequestCriteriaDao;
import com.example.internshipjava2026korolchuk.entity.Department;
import com.example.internshipjava2026korolchuk.entity.Employee;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.repository.DepartmentRepository;
import com.example.internshipjava2026korolchuk.repository.EmployeeRepository;
import com.example.internshipjava2026korolchuk.repository.TravelRequestRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TravelRequestCriteriaDao.class)
public class TravelRequestRepositoryTest extends BaseIntegrationTest {
    @Autowired
    private TravelRequestRepository travelRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void shouldUpdateTravelRequest() {
        Department dep = departmentRepository.findById(-1L).get();
        Employee emp = employeeRepository.findById(-1L).get();

        TravelRequest newTr = new TravelRequest(-1L, emp, dep, "Петербург", LocalDate.now(),
                LocalDate.now().plusDays(1), "Подписание договора", TravelRequestStatus.DRAFT,
                null, null, new BigDecimal("100.00"));
        TravelRequest savedTr = travelRequestRepository.saveAndFlush(newTr);

        entityManager.refresh(savedTr);

        assertEquals(newTr.getDestination(), savedTr.getDestination());
        assertNotEquals(BigDecimal.valueOf(100.00), savedTr.getEstimatedCost());
        assertNotNull(savedTr.getUpdatedAt());
    }

    @Test
    void shouldFindAllTravelRequests() {
        List<TravelRequest> trList = travelRequestRepository.findAll();

        assertEquals(2, trList.size());
        assertNotNull(trList.get(0));
        assertTrue(trList.stream().anyMatch(x -> x.getDestination().equals("Брест")));
    }

    @Test
    void shouldDeleteTravelRequest() {
        travelRequestRepository.deleteById(-1L);

        assertEquals(1, travelRequestRepository.count());
    }

    @Test
    void shouldNotFindTravelRequest() {
        Optional<TravelRequest> tr = travelRequestRepository.findById(-20L);

        assertTrue(tr.isEmpty());
    }
}
