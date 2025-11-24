package com.example.MutantesGlobal.repositories;

import com.example.MutantesGlobal.entities.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DnaRepository extends JpaRepository<Dna, Long> {
    Optional<Dna> findByHash(String hash);
    long countByMutantTrue();
    long countByMutantFalse();
}