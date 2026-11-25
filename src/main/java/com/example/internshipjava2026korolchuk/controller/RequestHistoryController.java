package com.example.internshipjava2026korolchuk.controller;

import com.example.internshipjava2026korolchuk.dto.RequestHistoryResponseDto;
import com.example.internshipjava2026korolchuk.service.RequestHistoryService;
import com.example.internshipjava2026korolchuk.specification.RequestHistoryFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Requests history")
@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
public class RequestHistoryController {

    private final RequestHistoryService requestHistoryService;

    @Tag(name = "Get")
    @Operation(summary = "Get all history records")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<RequestHistoryResponseDto> getWholeHistory(@Valid RequestHistoryFilter filter, Pageable pageable) {
        return requestHistoryService.getWholeHistory(filter, pageable);
    }

    @Tag(name = "Get")
    @Operation(summary = "Get specific history record by id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestHistoryResponseDto getHistoryRecordById(@PathVariable Long id) {
        return requestHistoryService.getHistoryRecordById(id);
    }

    @Tag(name = "Update")
    @Operation(summary = "Update specific history record by id")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RequestHistoryResponseDto updateHistoryRecord(@PathVariable Long id, @RequestParam String message) {
        return requestHistoryService.updateHistoryRecord(id, message);
    }
}
