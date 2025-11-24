package com.example.MutantesGlobal.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DnaResponse {

    private boolean mutant;
    private String message;

    public DnaResponse() {
    }
}