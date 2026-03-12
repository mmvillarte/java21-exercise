package org.exercise.user.application.usecase;

import org.exercise.user.application.command.model.UserCommand;
import org.exercise.user.application.command.model.UserCreator;
import org.exercise.user.application.command.model.UserDeletion;
import org.exercise.user.application.command.model.UserUpdate;
import org.exercise.user.application.command.service.UserCommandService;

public class UserCommandHandler {
    private final UserCommandService userCommandService;

    public UserCommandHandler(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    public UserCommandResult<?> handle(UserCommand command) {
        switch (command) {
            case UserCreator userCreator -> {
                return createUser(userCreator);
            }
            case UserUpdate userUpdate -> {
                return updateUser(userUpdate);
            }
            case UserDeletion userDeletion -> {
                return deleteUser(userDeletion);
            }
        }
    }

    private UserCommandResult<?> createUser(UserCreator userCreator) {
        try {
            return new UserSuccess<>(201, "User successfully created",
                    userCommandService.create(userCreator));
        } catch(Exception e) {
            return new UserError<>(500, "Failed to create user: " + e.getMessage());
        }
    }

    private UserCommandResult<?> updateUser(UserUpdate userUpdate) {
        try {
            return new UserSuccess<>(200, "User successfully updated",
                    userCommandService.update(userUpdate));
        } catch(Exception e) {
            return new UserError<>(500, "Failed to update user: " + e.getMessage());
        }
    }

    private UserCommandResult<?> deleteUser(UserDeletion userDeletion) {
        try {
            userCommandService.delete(userDeletion.id());
            return new UserSuccess<>(200, "User successfully deleted", null);
        } catch(Exception e) {
            return new UserError<>(500, "Failed to delete user: " + e.getMessage());
        }
    }
}
