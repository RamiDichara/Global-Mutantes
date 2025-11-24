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

    @PostMapping
    public ResponseEntity<Void> isMutant(@Valid @RequestBody DnaRequest dnaRequest) {
        boolean mutant = dnaService.isMutant(dnaRequest.getDna());

        if (mutant) {
            return ResponseEntity.ok().build(); // HTTP 200
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // HTTP 403
        }
    }
}


