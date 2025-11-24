package com.example.MutantesGlobal.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DnaServiceTest {

    private DnaService dnaService;

    @BeforeEach
    void setUp() {
        dnaService = new DnaService();
    }

    @Test
    void testMutantHorizontal() {
        String[] dna = {
                "ATTTTT",   // ← Fila mutante (TTTT)
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(dnaService.isMutant(dna));
    }

    @Test
    void testMutantVertical() {
        String[] dna = {
                "ATGC",
                "ATGC",
                "ATGC",
                "ATGC"
        };
        assertTrue(dnaService.isMutant(dna));
    }

    @Test
    void testMutantDiagonal() {
        String[] dna = {
                "ATGCA",
                "CAGTC",
                "TTATG",
                "GAAAA",
                "ACTAG"
        };
        assertTrue(dnaService.isMutant(dna));
    }

    @Test
    void testNonMutant() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TGAT",
                "AGAC"
        };
        assertFalse(dnaService.isMutant(dna));
    }

}