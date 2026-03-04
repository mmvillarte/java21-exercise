package org.exercise.user.api.model;

public record UserResult<T>(int code, String message, T data) {
}
