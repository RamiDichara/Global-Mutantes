package com.example.MutantesGlobal.controllers;

import com.example.MutantesGlobal.services.DnaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DnaController.class)
class DnaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DnaService dnaService;

    @Test
    void testMutantReturns200() throws Exception {
        String json = "{ \"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"] }";

        when(dnaService.isMutant(any())).thenReturn(true);

        mockMvc.perform(post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void testNonMutantReturns403() throws Exception {
        String json = "{ \"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCGCTA\",\"TCACTG\"] }";

        when(dnaService.isMutant(any())).thenReturn(false);

        mockMvc.perform(post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isForbidden());
    }
}