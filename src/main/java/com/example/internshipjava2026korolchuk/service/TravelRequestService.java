package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.TravelRequestCreationDto;
import com.example.internshipjava2026korolchuk.dto.TravelRequestResponseDto;
import com.example.internshipjava2026korolchuk.dto.TravelRequestUpdatingDto;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.entity.TravelRequestStatus;
import com.example.internshipjava2026korolchuk.exception.CannotUpdateTravelRequestWithStatusException;
import com.example.internshipjava2026korolchuk.exception.NotValidDtoException;
import com.example.internshipjava2026korolchuk.mapper.TravelRequestMapper;
import com.example.internshipjava2026korolchuk.repository.TravelRequestRepository;
import com.example.internshipjava2026korolchuk.specification.TravelRequestFilter;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Metrics;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import com.example.starter.logging.Logginable;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class TravelRequestService {

    private final TravelRequestRepository travelRequestRepository;
    private final TravelRequestMapper travelRequestMapper;
    private final RequestHistoryService requestHistoryService;

    private AtomicLong rejectedRequestsCounter;
    private final DistributionSummary requestsDurationSummary = DistributionSummary
            .builder("requests.duration")
            .description("Продолжительность командировок")
            .baseUnit("дни")
            .register(Metrics.globalRegistry);

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void registerRejectedRequestsMetric() {
        long count = travelRequestRepository.countByStatus(TravelRequestStatus.REJECTED);
        rejectedRequestsCounter = new AtomicLong(count);
        Metrics.gauge("requests.rejected.currently", rejectedRequestsCounter);
    }

    @Transactional(readOnly = true)
    @Logginable
    public Page<TravelRequestResponseDto> getAllTravelRequests(TravelRequestFilter filter, Pageable pageable) {
        Specification<TravelRequest> spec = TravelRequestFilter.byFilter(filter);
        Page<TravelRequest> page = travelRequestRepository.findAll(spec, pageable);

        return page.map(travelRequestMapper::toDto);
    }

    @Transactional(readOnly = true)
    @Logginable
    public TravelRequestResponseDto getTravelRequestById(Long id) {
        return travelRequestMapper.toDto(travelRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заявка с id '" + id + "' не найдена")));
    }

    @Transactional
    @Logginable
    public TravelRequestResponseDto createTravelRequest(TravelRequestCreationDto dto) {
        if (dto.departmentId() == null || dto.employeeId() == null) {
            throw new NotValidDtoException("Заявка не может существовать без отдела или сотрудника");
        }

        TravelRequest tr = travelRequestRepository.saveAndFlush(travelRequestMapper.toEntity(dto));
        entityManager.refresh(tr);

        requestsDurationSummary.record(ChronoUnit.DAYS.between(tr.getStartDate(), tr.getEndDate()) + 1);

        return travelRequestMapper.toDto(tr);
    }

    @Transactional
    @Logginable
    public TravelRequestResponseDto updateTravelRequest(Long id, TravelRequestUpdatingDto dto) {
        TravelRequest travelRequest = travelRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заявка с id '" + id + "' не найдена"));

        TravelRequestStatus status = travelRequest.getStatus();

        if (status == TravelRequestStatus.APPROVED || status == TravelRequestStatus.REJECTED) {
            throw new CannotUpdateTravelRequestWithStatusException(
                    "Нельзя обновлять заявку в статусе " + status);
        }

        travelRequestMapper.updateTravelRequestFromDto(dto, travelRequest);

        TravelRequest tr = travelRequestRepository.saveAndFlush(travelRequest);
        entityManager.refresh(tr);

        return travelRequestMapper.toDto(tr);
    }

    @Transactional
    @Logginable
    public TravelRequestResponseDto updateTravelRequestStatus(Long id, TravelRequestStatus newStatus) {
        TravelRequest travelRequest = travelRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заявка с id '" + id + "' не найдена"));

        if (travelRequest.getStatus() == newStatus) {
            throw new CannotUpdateTravelRequestWithStatusException("Заявка уже находится в указанном статусе");
        }

        TravelRequestStatus oldStatus = travelRequest.getStatus();
        travelRequest.setStatus(newStatus);

        TravelRequest savedTravelRequest = travelRequestRepository.save(travelRequest);
        requestHistoryService.createHistoryRecord(travelRequest, oldStatus);

        if (newStatus == TravelRequestStatus.REJECTED) {
            rejectedRequestsCounter.incrementAndGet();
        } else if (oldStatus == TravelRequestStatus.REJECTED) {
            if (rejectedRequestsCounter.get() > 0) {
                rejectedRequestsCounter.decrementAndGet();
            }
        }

        return travelRequestMapper.toDto(savedTravelRequest);
    }

    @Transactional
    @Logginable
    public void deleteTravelRequest(Long id) {
        travelRequestRepository.deleteById(id);
    }

    @Transactional
    @Logginable
    public long countByEmployee(Long id) {
        return travelRequestRepository.countByEmployeeId(id);
    }

    @Transactional
    @Logginable
    public long countByDepartment(Long id) {
        return travelRequestRepository.countByDepartmentId(id);
    }
}
