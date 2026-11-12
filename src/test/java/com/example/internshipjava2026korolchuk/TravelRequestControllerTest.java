package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dto.TravelRequestCreationDto;
import com.example.internshipjava2026korolchuk.dto.TravelRequestUpdatingDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.json.JsonMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class TravelRequestControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonMapper jsonMapper;

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/api/v1/travel-requests")
                        .param("page", "0")
                        .param("size", "5")
                        .param("sort", "id,desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].id").value(-1))
                .andExpect(jsonPath("$.content[*].destination").value(hasItem("Москва")));
    }

    @Test
    void testGetOne() throws Exception {
        mockMvc.perform(get("/api/v1/travel-requests/-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(-1))
                .andExpect(jsonPath("$.destination").value("Москва"));
    }

    @Test
    void testCreate() throws Exception {
        TravelRequestCreationDto dto = new TravelRequestCreationDto(-1L, -1L,
                "Moscow", LocalDate.parse("2026-01-20"),
                LocalDate.parse("2026-01-23"), "Random purpose", BigDecimal.valueOf(10.0));

        mockMvc.perform(post("/api/v1/travel-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.destination").value("Moscow"))
                .andExpect(jsonPath("$.purpose").value("Random purpose"));
    }

    @Test
    void testUpdate() throws Exception {
        TravelRequestUpdatingDto dto = new TravelRequestUpdatingDto("Moscow", LocalDate.parse("2026-01-20"),
                LocalDate.parse("2026-01-23"), "Random purpose", BigDecimal.valueOf(10.0));

        mockMvc.perform(put("/api/v1/travel-requests/-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.destination").value("Moscow"))
                .andExpect(jsonPath("$.purpose").value("Random purpose"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/travel-requests/-1"))
                .andExpect(status().isNoContent());
    }
}
