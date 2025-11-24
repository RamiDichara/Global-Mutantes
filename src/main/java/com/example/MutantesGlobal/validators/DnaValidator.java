package com.example.MutantesGlobal.validators;


import com.example.MutantesGlobal.dto.DnaRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<ValidDna, DnaRequest> {

    private static final char[] VALID_CHARS = {'A', 'T', 'C', 'G'};

    @Override
    public boolean isValid(DnaRequest value, ConstraintValidatorContext context) {

        if (value == null || value.getDna() == null) {
            return buildViolation(context, "El ADN no puede ser nulo");
        }

        if (value.getDna().length == 0) {
            return buildViolation(context, "El ADN no puede estar vacío");
        }

        int n = value.getDna().length;

        for (String row : value.getDna()) {

            if (row == null) {
                return buildViolation(context, "Ninguna fila del ADN puede ser nula");
            }

            if (row.length() != n) {
                return buildViolation(context, "El ADN debe ser una matriz NxN");
            }

            for (char c : row.toCharArray()) {
                if (!isValidChar(c)) {
                    return buildViolation(context, "Caracter inválido en el ADN. Solo se permiten A,T,C,G");
                }
            }
        }

        return true;
    }

    private boolean buildViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode("dna")
                .addConstraintViolation();
        return false;
    }

    private boolean isValidChar(char c) {
        for (char valid : VALID_CHARS) {
            if (valid == c) return true;
        }
        return false;
    }
}