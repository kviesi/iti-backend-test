package com.example.iti.rest;

import com.example.iti.domain.PasswordValidateService;
import com.example.iti.rest.request.PasswordValidateRequest;
import com.example.iti.rest.response.PasswordValidateResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "password")
public class PasswordValidatorRestController {
    private final PasswordValidateService passwordValidateService;

    @PostMapping(
        value = "/v1/password/validate",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PasswordValidateResponse validate(@RequestBody PasswordValidateRequest request) {
        boolean valid = passwordValidateService.isValid(request.getPassword());
        return PasswordValidateResponse.builder().valid(valid).build();
    }

}
