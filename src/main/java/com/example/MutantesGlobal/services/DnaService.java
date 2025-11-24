package com.example.MutantesGlobal.services;

import com.example.MutantesGlobal.entities.Dna;
import com.example.MutantesGlobal.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class DnaService {

    @Autowired
    private DnaRepository dnaRepository;

    private static final int SEQ = 4;
    private static final char[] VALID_CHARS = {'A', 'T', 'C', 'G'};

    public boolean isMutant(String[] dna) {
        validateDna(dna);

        int n = dna.length;
        char[][] matrix = toMatrix(dna);

        int count = 0;

        // Horizontal
        for (int i = 0; i < n; i++) {
            count += countRow(matrix, i);
            if (count > 1) return true;
        }

        // Vertical
        for (int j = 0; j < n; j++) {
            count += countColumn(matrix, j);
            if (count > 1) return true;
        }

        // Diagonal ↘
        count += countDiagonalRight(matrix);
        if (count > 1) return true;

        // Diagonal ↙
        count += countDiagonalLeft(matrix);
        return count > 1;
    }

    // -------------------------
    // VALIDACIONES
    // -------------------------

    private void validateDna(String[] dna) {
        if (dna == null || dna.length == 0)
            throw new IllegalArgumentException("El ADN no puede ser nulo o vacío.");

        int n = dna.length;

        for (String row : dna) {
            if (row.length() != n)
                throw new IllegalArgumentException("El ADN debe ser una matriz NxN.");

            for (char c : row.toCharArray()) {
                if (!isValidChar(c))
                    throw new IllegalArgumentException("ADN contiene caracteres inválidos: " + c);
            }
        }
    }

    private boolean isValidChar(char c) {
        for (char valid : VALID_CHARS) {
            if (valid == c) return true;
        }
        return false;
    }

    private char[][] toMatrix(String[] dna) {
        int n = dna.length;
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++)
            matrix[i] = dna[i].toCharArray();
        return matrix;
    }


    // -------------------------
    // BÚSQUEDA DE SECUENCIAS
    // -------------------------

    private int countRow(char[][] m, int row) {
        int n = m.length;
        int count = 0, seq = 1;

        for (int col = 1; col < n; col++) {
            if (m[row][col] == m[row][col - 1]) {
                seq++;
                if (seq == SEQ) count++;
            } else {
                seq = 1;
            }
        }
        return count;
    }

    private int countColumn(char[][] m, int col) {
        int n = m.length;
        int count = 0, seq = 1;

        for (int row = 1; row < n; row++) {
            if (m[row][col] == m[row - 1][col]) {
                seq++;
                if (seq == SEQ) count++;
            } else {
                seq = 1;
            }
        }
        return count;
    }

    private int countDiagonalRight(char[][] m) {
        int n = m.length;
        int count = 0;

        // diagonales que empiezan en primera fila
        for (int colStart = 0; colStart < n - 3; colStart++) {
            int seq = 1;
            for (int i = 1; i < n - colStart; i++) {
                if (m[i][colStart + i] == m[i - 1][colStart + i - 1]) {
                    seq++;
                    if (seq == SEQ) count++;
                } else seq = 1;
            }
        }

        // diagonales que empiezan en primera columna (excepto 0,0)
        for (int rowStart = 1; rowStart < n - 3; rowStart++) {
            int seq = 1;
            for (int i = 1; i < n - rowStart; i++) {
                if (m[rowStart + i][i] == m[rowStart + i - 1][i - 1]) {
                    seq++;
                    if (seq == SEQ) count++;
                } else seq = 1;
            }
        }

        return count;
    }

    private int countDiagonalLeft(char[][] m) {
        int n = m.length;
        int count = 0;

        // diagonales que empiezan en la primera fila
        for (int colStart = 3; colStart < n; colStart++) {
            int seq = 1;
            for (int i = 1; i <= colStart; i++) {
                if (m[i][colStart - i] == m[i - 1][colStart - (i - 1)]) {
                    seq++;
                    if (seq == SEQ) count++;
                } else seq = 1;
            }
        }

        // diagonales que empiezan en la última columna (excepto fila 0)
        for (int rowStart = 1; rowStart < n - 3; rowStart++) {
            int seq = 1;
            for (int i = 1; rowStart + i < n; i++) {
                if (m[rowStart + i][n - 1 - i] == m[rowStart + i - 1][n - i]) {
                    seq++;
                    if (seq == SEQ) count++;
                } else seq = 1;
            }
        }

        return count;
    }

    public void saveDna(String[] dna) {
        boolean mutant = isMutant(dna);
        String hash = hashDna(dna);

        dnaRepository.findByHash(hash).orElseGet(() -> {
            Dna entity = new Dna(hash, mutant);
            return dnaRepository.save(entity);
        });
    }

    private String hashDna(String[] dna) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            for (String row : dna) {
                md.update(row.getBytes());
            }
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generando hash ADN", e);
        }
    }

}