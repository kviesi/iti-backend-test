package com.example.iti.domain;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class PasswordValidateService {
    private static final int MIN_LENGTH = 9;
    private static final Pattern SPECIAL_CHARACTERS = Pattern.compile("[a-z0-9]*", Pattern.CASE_INSENSITIVE);
    private static final Pattern DIGIT = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
    private static final Pattern UPPER = Pattern.compile("[A-Z]");
    private static final Pattern LOWER = Pattern.compile("[a-z]");

    public boolean isValid(String password) {
        return isNotBlank(password) &&
                isGreaterThanMinLength(password) &&
                hasAtLeastOneDigit(password) &&
                hasAtLeastOneUpperCaseCharacter(password) &&
                hasAtLeastOneLowerCaseCharacter(password) &&
                hasSpecialCharacter(password) &&
                !hasRepeatedCharacter(password);
    }

    private boolean isGreaterThanMinLength(String password) {
        return password.length() >= MIN_LENGTH;
    }

    private boolean hasAtLeastOneDigit(String password) {
        return DIGIT.matcher(password).find();
    }

    private boolean hasAtLeastOneUpperCaseCharacter(String password) {
        return UPPER.matcher(password).find();
    }

    private boolean hasAtLeastOneLowerCaseCharacter(String password) {
        return LOWER.matcher(password).find();
    }

    private boolean hasSpecialCharacter(String password) {
        return !SPECIAL_CHARACTERS.matcher(password).matches();
    }

    private boolean hasRepeatedCharacter(String password) {
        return password.length() != password.chars().distinct().count();
    }

}
