package com.example.iti.rest;

import com.example.iti.domain.PasswordValidateService;
import com.example.iti.rest.request.PasswordValidateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PasswordValidatorRestControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PasswordValidateService passwordValidateService;

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    @SneakyThrows
    public void testIsValid(boolean valid) {
        String password = RandomStringUtils.randomAlphabetic(10);

        when(passwordValidateService.isValid(password)).thenReturn(valid);

        PasswordValidateRequest request = new PasswordValidateRequest();
        request.setPassword(password);

        mockMvc.perform(
            post("/v1/password/validate")
            .content(asJsonString(request))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.valid").value(Boolean.valueOf(valid).toString()));
    }

    @Test
    @SneakyThrows
    public void testErrorWhenExceptionIsThrows() {
        String password = RandomStringUtils.randomAlphabetic(10);

        when(passwordValidateService.isValid(password)).thenThrow(new RuntimeException("test error"));

        PasswordValidateRequest request = new PasswordValidateRequest();
        request.setPassword(password);

        mockMvc.perform(
            post("/v1/password/validate")
            .content(asJsonString(request))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isInternalServerError())
        .andExpect(jsonPath("$.valid").doesNotExist());
    }

    @SneakyThrows
    private String asJsonString(PasswordValidateRequest request) {
        return OBJECT_MAPPER.writeValueAsString(request);
    }

}
