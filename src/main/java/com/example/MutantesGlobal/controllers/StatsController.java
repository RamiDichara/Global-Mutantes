package com.example.MutantesGlobal.controllers;

import com.example.MutantesGlobal.services.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/stats")
    @Operation(
            summary = "Obtiene las estadísticas de verificaciones de ADN",
            description = "Devuelve la cantidad de humanos, mutantes y el ratio entre ambos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estadísticas obtenidas correctamente")
            }
    )
    public Map<String, Object> getStats() {
        return statsService.getStats();
    }
}