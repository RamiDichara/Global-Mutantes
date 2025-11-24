package com.example.MutantesGlobal.dto;

import com.example.MutantesGlobal.validators.ValidDna;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidDna
public class DnaRequest {

    @NotNull(message = "El ADN no puede ser nulo")
    private String[] dna;

    public DnaRequest() {
    }

    public DnaRequest(String[] dna) {
        this.dna = dna;
    }
        
}