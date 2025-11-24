package com.example.MutantesGlobal.services;

import com.example.MutantesGlobal.repositories.DnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class StatsService {

    private final DnaRepository dnaRepository;

    public Map<String, Object> getStats() {
        long countMutant = dnaRepository.countByMutantTrue();
        long countHuman = dnaRepository.countByMutantFalse();
        double ratio = countHuman == 0 ? 0 : (double) countMutant / countHuman;

        Map<String, Object> stats = new HashMap<>();
        stats.put("count_mutant_dna", countMutant);
        stats.put("count_human_dna", countHuman);
        stats.put("ratio", ratio);

        return stats;
    }
}