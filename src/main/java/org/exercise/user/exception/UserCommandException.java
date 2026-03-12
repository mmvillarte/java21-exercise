package org.exercise.user.exception;

public class UserCommandException extends RuntimeException {
    public UserCommandException(String message) {
        super(message);
    }
}
