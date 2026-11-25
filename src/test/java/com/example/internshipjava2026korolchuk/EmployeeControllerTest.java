package com.example.internshipjava2026korolchuk;

import com.example.internshipjava2026korolchuk.dto.EmployeeCreationDto;
import com.example.internshipjava2026korolchuk.dto.EmployeeResponseDto;
import com.example.internshipjava2026korolchuk.dto.EmployeeUpdatingDto;
import com.example.internshipjava2026korolchuk.dto.ExceptionDto;
import com.example.internshipjava2026korolchuk.exception.CannotDeleteEntityException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

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
public class EmployeeControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonMapper jsonMapper;

    @Test
    void testGetAll() throws Exception {
        String result = mockMvc.perform(get("/api/v1/employees")
                        .param("page", "0")
                        .param("size", "5")
                        .param("sort", "id,desc"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode page = jsonMapper.readTree(result);
        JsonNode content = page.path("content");
        List<EmployeeResponseDto> emps = jsonMapper.convertValue(content, new TypeReference<>() {
        });

        assertNotNull(emps);
        assertEquals(3, page.path("totalElements").asLong());
        assertTrue(emps.stream().anyMatch(x -> x.id() == -1L));
        assertTrue(emps.stream().anyMatch(x -> x.email().equals("artem@mail.ru")));
    }

    @Test
    void testGetOne() throws Exception {
        String result = mockMvc.perform(get("/api/v1/employees/-1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        EmployeeResponseDto emp = jsonMapper.readValue(result, EmployeeResponseDto.class);
        assertNotNull(emp);
        assertEquals(-1L, emp.id());
        assertEquals("artem@mail.ru", emp.email());
    }

    @Test
    void testCreate() throws Exception {
        final String email = "petr@mail.ru";

        EmployeeCreationDto dto = new EmployeeCreationDto("Petr Yan", email,
                "director", -1L, LocalDate.parse("2025-11-03"));

        String result = mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        EmployeeResponseDto emp = jsonMapper.readValue(result, EmployeeResponseDto.class);
        assertNotNull(emp);
        assertNotNull(emp.id());
        assertEquals(email, emp.email());
    }

    @Test
    void testUpdate() throws Exception {
        final String email = "petr@mail.ru";

        EmployeeUpdatingDto dto = new EmployeeUpdatingDto("Petr Yan",
                email, "director");

        String result = mockMvc.perform(put("/api/v1/employees/-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        EmployeeResponseDto emp = jsonMapper.readValue(result, EmployeeResponseDto.class);
        assertNotNull(emp);
        assertEquals(-1L, emp.id());
        assertEquals(email, emp.email());
    }

    @Test
    void testDelete() throws Exception {
        String result = mockMvc.perform(delete("/api/v1/employees/-1"))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ExceptionDto ex = jsonMapper.readValue(result, ExceptionDto.class);
        assertNotNull(ex);
        assertEquals(CannotDeleteEntityException.class.getName(), ex.getClassName());
    }
}
