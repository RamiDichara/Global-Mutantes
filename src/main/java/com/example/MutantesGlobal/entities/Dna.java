package com.example.MutantesGlobal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "dna")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 1000)
    private String hash;

    @Column(nullable = false)
    private boolean mutant;

    public Dna(String hash, boolean mutant) {
        this.hash = hash;
        this.mutant = mutant;
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