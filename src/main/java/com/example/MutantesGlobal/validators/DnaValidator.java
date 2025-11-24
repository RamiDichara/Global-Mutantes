package com.example.MutantesGlobal.validators;


import com.example.MutantesGlobal.dto.DnaRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<ValidDna, DnaRequest> {

    private static final char[] VALID_CHARS = {'A', 'T', 'C', 'G'};

    @Override
    public boolean isValid(DnaRequest value, ConstraintValidatorContext context) {
        if (value == null || value.getDna() == null || value.getDna().length == 0) {
            return false;
        }

        int n = value.getDna().length;

        for (String row : value.getDna()) {
            if (row.length() != n) {
                return false;
            }
            for (char c : row.toCharArray()) {
                if (!isValidChar(c)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValidChar(char c) {
        for (char valid : VALID_CHARS) {
            if (valid == c) return true;
        }
        return false;
    }
}