package com.example.MutantesGlobal.controllers;

import com.example.MutantesGlobal.dto.DnaRequest;
import com.example.MutantesGlobal.services.DnaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mutant")
public class DnaController {

    private final DnaService dnaService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Void> isMutant(@Valid @RequestBody DnaRequest dnaRequest) {

        boolean mutant = dnaService.isMutant(dnaRequest.getDna());
        dnaService.saveDna(mutant, dnaRequest.getDna());

        return mutant
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}


