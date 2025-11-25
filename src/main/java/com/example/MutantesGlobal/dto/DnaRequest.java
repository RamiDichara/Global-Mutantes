package com.example.MutantesGlobal.dto;

import com.example.MutantesGlobal.validators.ValidDna;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidDna
public class DnaRequest {

    @NotNull(message = "El ADN no puede ser nulo")
    @NotEmpty(message = "El ADN no puede estar vacío")
    @Schema(
            description = "Array de secuencias de ADN",
            example = "[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]"
    )
    private String[] dna;

    public DnaRequest() {
    }

    public DnaRequest(String[] dna) {
        this.dna = dna;
    }
        
}