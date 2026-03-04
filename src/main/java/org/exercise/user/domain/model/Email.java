package org.exercise.user.domain.model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private final String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String email) {
        Objects.requireNonNull(email, "Email cannot be null");

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
        return new Email(email.toLowerCase());
    }

    public String value() {
        return value;
    }
}
