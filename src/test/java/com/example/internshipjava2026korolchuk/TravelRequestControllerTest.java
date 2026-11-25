package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dto.TravelRequestCreationDto;
import com.example.internshipjava2026korolchuk.dto.TravelRequestResponseDto;
import com.example.internshipjava2026korolchuk.dto.TravelRequestUpdatingDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class TravelRequestControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonMapper jsonMapper;

    @Test
    void testGetAll() throws Exception {
        String result = mockMvc.perform(get("/api/v1/travel-requests")
                        .param("page", "0")
                        .param("size", "5")
                        .param("sort", "id,desc"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode page = jsonMapper.readTree(result);
        JsonNode content = page.path("content");
        List<TravelRequestResponseDto> reqs = jsonMapper.convertValue(content, new TypeReference<>() {
        });

        assertNotNull(reqs);
        assertEquals(2, page.path("totalElements").asLong());
        assertTrue(reqs.stream().anyMatch(x -> x.id() == -1L));
        assertTrue(reqs.stream().anyMatch(x -> x.destination().equals("Москва")));
    }

    @Test
    void testGetOne() throws Exception {
        String result = mockMvc.perform(get("/api/v1/travel-requests/-1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        TravelRequestResponseDto dto = jsonMapper.readValue(result, TravelRequestResponseDto.class);
        assertEquals(-1L, dto.id());
        assertEquals("Москва", dto.destination());
    }

    @Test
    void testCreate() throws Exception {
        final String dest = "Берлин";

        TravelRequestCreationDto dto = new TravelRequestCreationDto(-1L, -1L,
                dest, LocalDate.parse("2026-01-20"),
                LocalDate.parse("2026-01-23"), "Random purpose", BigDecimal.valueOf(10.0));

        String result = mockMvc.perform(post("/api/v1/travel-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        TravelRequestResponseDto req = jsonMapper.readValue(result, TravelRequestResponseDto.class);
        assertNotNull(req);
        assertNotNull(req.id());
        assertEquals(dest, req.destination());
    }

    @Test
    void testUpdate() throws Exception {
        final String dest = "Moscow";

        TravelRequestUpdatingDto dto = new TravelRequestUpdatingDto(dest,
                LocalDate.parse("2026-01-20"),
                LocalDate.parse("2026-01-23"), "Random purpose", BigDecimal.valueOf(10.0));

        String result = mockMvc.perform(put("/api/v1/travel-requests/-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        TravelRequestResponseDto req = jsonMapper.readValue(result, TravelRequestResponseDto.class);
        assertNotNull(req);
        assertEquals(dest, req.destination());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/travel-requests/-1"))
                .andExpect(status().isNoContent());
    }
}
