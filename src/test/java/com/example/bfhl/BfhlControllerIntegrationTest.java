package com.example.bfhl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BfhlControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String toJson(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void postBfhl_standardInput_returns200() throws Exception {
        Map<String, Object> request = new HashMap<>();
        request.put("data", Arrays.asList("a", "1", "334", "4", "R", "$"));

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.user_id").value("ansh_khatri_26052026"))
                .andExpect(jsonPath("$.email").value("anshkhatri231038@acropolis.in"))
                .andExpect(jsonPath("$.roll_number").value("0827CS231042"))
                .andExpect(jsonPath("$.odd_numbers", contains("1")))
                .andExpect(jsonPath("$.even_numbers", contains("334", "4")))
                .andExpect(jsonPath("$.alphabets", contains("A", "R")))
                .andExpect(jsonPath("$.special_characters", contains("$")))
                .andExpect(jsonPath("$.sum").value("339"))
                .andExpect(jsonPath("$.concat_string").value("Ra"));
    }

    @Test
    void postBfhl_emptyData_returns200() throws Exception {
        Map<String, Object> request = new HashMap<>();
        request.put("data", Arrays.asList());

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.sum").value("0"))
                .andExpect(jsonPath("$.concat_string").value(""))
                .andExpect(jsonPath("$.odd_numbers", hasSize(0)))
                .andExpect(jsonPath("$.even_numbers", hasSize(0)));
    }

    @Test
    void postBfhl_nullData_returns400() throws Exception {
        String body = "{\"data\": null}";

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false));
    }

    @Test
    void postBfhl_missingDataField_returns400() throws Exception {
        String body = "{}";

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false));
    }

    @Test
    void postBfhl_invalidJson_returns400() throws Exception {
        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("not-json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false));
    }

    @Test
    void postBfhl_concatStringLogic() throws Exception {
        Map<String, Object> request = new HashMap<>();
        request.put("data", Arrays.asList("A", "ABCD", "DOE"));

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.concat_string").value("EoDdCbAa"));
    }

    @Test
    void postBfhl_alphabetsAreUppercase() throws Exception {
        Map<String, Object> request = new HashMap<>();
        request.put("data", Arrays.asList("hello", "world"));

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.alphabets[0]").value("HELLO"))
                .andExpect(jsonPath("$.alphabets[1]").value("WORLD"));
    }

    @Test
    void postBfhl_contentTypeJson_isRequired() throws Exception {
        Map<String, Object> request = new HashMap<>();
        request.put("data", Arrays.asList("1", "2"));

        mockMvc.perform(post("/bfhl")
                        .content(toJson(request)))
                .andExpect(status().isUnsupportedMediaType());
    }
}
