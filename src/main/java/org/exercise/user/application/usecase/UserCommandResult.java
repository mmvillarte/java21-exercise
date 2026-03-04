package org.exercise.user.application.usecase;

import org.exercise.user.api.model.UserResult;

public sealed interface UserCommandResult<T> permits UserSuccess, UserError {

    UserResult<T> toUserResult();

}
