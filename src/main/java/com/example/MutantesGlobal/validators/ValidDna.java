package com.example.MutantesGlobal.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DnaValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDna {
    String message() default "ADN inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}