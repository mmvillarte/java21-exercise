package org.exercise.user.domain.dto;

public record UserDTO (
        String firstName,
        String lastName,
        String email,
        String dni
) {}
