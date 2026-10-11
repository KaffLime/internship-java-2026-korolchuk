package com.example.internshipjava2026korolchuk.service;

import com.example.internshipjava2026korolchuk.dto.TravelRequestDto;
import com.example.internshipjava2026korolchuk.entity.RequestHistory;
import com.example.internshipjava2026korolchuk.entity.TravelRequest;
import com.example.internshipjava2026korolchuk.repository.RequestHistoryRepository;
import com.example.internshipjava2026korolchuk.repository.TravelRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestHistoryService {
    private final RequestHistoryRepository requestHistoryRepository;
    private final TravelRequestRepository travelRequestRepository;

    @Transactional(readOnly = true)
    public List<RequestHistory> getWholeHistory() {
        return requestHistoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public RequestHistory getHistoryRecordById(Long id) {
        return requestHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Записи в истории с таким id не существует"));
    }

    @Transactional
    public void createHistoryRecord(Long travelRequestId, TravelRequestDto travelRequest,
                                    TravelRequestDto updatedTravelRequest) {
        TravelRequest foundTravelRequest = travelRequestRepository.findById(travelRequestId)
                .orElseThrow(() -> new RuntimeException("Заявки с таким id не существует"));

        requestHistoryRepository.save(new RequestHistory(
                null, foundTravelRequest, travelRequest.status(),
                updatedTravelRequest.status(), null, "Запись успешно добавлена")
        );
    }

    @Transactional
    public RequestHistory updateHistoryRecord(Long id, String comment) {
        RequestHistory foundRequestHistory = requestHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Записи в истории с таким id не существует"));

        foundRequestHistory.setComment(comment);

        return requestHistoryRepository.save(foundRequestHistory);
    }

    @Transactional
    public void deleteRequestHistory(Long id) {
        requestHistoryRepository.deleteById(id);
    }
}
