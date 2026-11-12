package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dto.DepartmentCreationDto;
import com.example.internshipjava2026korolchuk.dto.DepartmentResponseDto;
import com.example.internshipjava2026korolchuk.dto.DepartmentUpdatingDto;
import com.example.internshipjava2026korolchuk.dto.ExceptionDto;
import com.example.internshipjava2026korolchuk.exception.CannotDeleteEntityException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureMockMvc
public class DepartmentControllerTest extends BaseIntegrationTest {

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void testGetAll() {
        ResponseEntity<Page<DepartmentResponseDto>> result = restTemplate
                .exchange("/api/v1/departments", HttpMethod.GET, HttpEntity.EMPTY,
                        new ParameterizedTypeReference<>() {});

        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().getTotalElements());
        assertEquals(1, result.getBody().getTotalPages());
        assertTrue(result.getBody().stream().anyMatch(x -> x.name().equals("OGT")));
    }

    @Test
    void testGetOne() {
        ResponseEntity<DepartmentResponseDto> result = restTemplate.
                getForEntity("/api/v1/departments/-1", DepartmentResponseDto.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(-1L, result.getBody().id());
        assertEquals("OGT", result.getBody().name());
    }

    @Test
    void testCreate() {
        DepartmentCreationDto dep = new DepartmentCreationDto("PEO", "12515");

        ResponseEntity<DepartmentResponseDto> result = restTemplate
                .postForEntity("/api/v1/departments", dep, DepartmentResponseDto.class);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertNotNull(result.getBody().id());
        assertEquals("PEO", result.getBody().name());
    }

    @Test
    void testUpdate() {
        DepartmentUpdatingDto req = new DepartmentUpdatingDto("SNAB", "52356");
        HttpEntity<DepartmentUpdatingDto> httpReq = new HttpEntity<>(req);

        ResponseEntity<DepartmentResponseDto> result = restTemplate
                .exchange("/api/v1/departments/-1", HttpMethod.PUT, httpReq, DepartmentResponseDto.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(-1L, result.getBody().id());
        assertEquals("SNAB", result.getBody().name());
    }

    @Test
    void testDelete() {
        ResponseEntity<ExceptionDto> result = restTemplate
                .exchange("/api/v1/departments/-1", HttpMethod.DELETE, HttpEntity.EMPTY, ExceptionDto.class);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(CannotDeleteEntityException.class.getName(), result.getBody().getClassName());
    }
}
