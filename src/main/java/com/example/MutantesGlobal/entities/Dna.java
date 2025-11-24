package com.example.MutantesGlobal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "dna")
public class Dna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(unique = true, nullable = false, length = 1000)
    private String hash; // Hash único del ADN

    @Setter
    @Column(nullable = false)
    private boolean mutant;

    public Dna(String hash, boolean mutant) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dna)) return false;
        Dna dna = (Dna) o;
        return Objects.equals(hash, dna.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash);
    }
}