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
                "AAAA",
                "CAGT",
                "TTAT",
                "AGAC"
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
                "ATGC",
                "CAGT",
                "TATA",
                "GAAA"
        };
        assertTrue(dnaService.isMutant(dna));
    }

    @Test
    void testNonMutant() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TTAT",
                "AGAC"
        };
        assertFalse(dnaService.isMutant(dna));
    }
    
}