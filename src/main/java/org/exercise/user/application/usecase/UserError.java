package org.exercise.user.application.usecase;

import org.exercise.user.api.model.UserResult;

public record UserError<T>(int code, String message) implements UserCommandResult<T> {
    @Override
    public UserResult<T> toUserResult() {
        return new UserResult<>(code, message, null);
    }
}
