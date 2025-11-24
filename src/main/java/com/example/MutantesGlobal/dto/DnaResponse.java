package com.example.MutantesGlobal.dto;


import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DnaResponse {

    private boolean mutant;
    @Setter
    private String message;

    public DnaResponse() {
    }

    public boolean isMutant() {
        return mutant;
    }

}