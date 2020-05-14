package com.example.iti.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PasswordValidateServiceTest {

    @Autowired
    private PasswordValidateService passwordValidateService;

    @ParameterizedTest
    @ValueSource(strings = {
            "AbTp9!fok",
    })
    public void testIsValidTrue(String password) {
        boolean valid = passwordValidateService.isValid(password);
        assertTrue(valid);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "aa",
            "ab",
            "AAAbbbCc",
            "AbTp9!foo",
            "AbcdefghI"
    })
    public void testIsValidFalse(String password) {
        boolean valid = passwordValidateService.isValid(password);
        assertFalse(valid);
    }

}
