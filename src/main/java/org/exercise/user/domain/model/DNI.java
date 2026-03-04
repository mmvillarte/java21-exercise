package org.exercise.user.domain.model;

import java.util.Objects;
import java.util.regex.Pattern;

public class DNI {
    private static final Pattern DNI_PATTERN =
            Pattern.compile("^\\d{8}[A-Z]$");

    private final String value;

    private DNI(String value) {
        this.value = value;
    }

    public static DNI of(String dni) {
        Objects.requireNonNull(dni, "DNI cannot be null");

        String normalized = dni.toUpperCase();

        if (!DNI_PATTERN.matcher(normalized).matches()) {
            throw new IllegalArgumentException("Invalid DNI: " + dni);
        }

        // DNI Letter Validation
        if (!isValidLetter(normalized)) {
            throw new IllegalArgumentException("Invalid DNI Letter: " + dni);
        }

        return new DNI(normalized);
    }

    private static boolean isValidLetter(String dni) {
        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int number = Integer.parseInt(dni.substring(0, 8));
        char expected = letters.charAt(number % 23);
        return dni.charAt(8) == expected;
    }

    public String value() {
        return value;
    }
}
