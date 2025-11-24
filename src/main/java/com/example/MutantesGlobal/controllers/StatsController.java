package com.example.MutantesGlobal.controllers;

import com.example.MutantesGlobal.services.StatsService;
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
    public Map<String, Object> getStats() {
        return statsService.getStats();
    }
}