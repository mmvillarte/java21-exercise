package org.exercise.user.application.usecase;

import org.exercise.user.application.command.CreateUser;
import org.exercise.user.application.command.DeleteUser;
import org.exercise.user.application.command.UpdateUser;
import org.exercise.user.application.command.UserCommand;
import org.exercise.user.application.command.service.UserCommandService;
import org.exercise.user.infrastructure.persistence.model.UserEntity;

public class UserCommandHandler {
    private final UserCommandService userService;

    public UserCommandHandler(UserCommandService userService) {
        this.userService = userService;
    }

    public UserCommandResult<?> handle(UserCommand command) {

        switch (command) {
            case CreateUser c -> {
                UserEntity userEntity = userService.create(c);
                if(userEntity == null) {
                    return new UserError<>(500, "Failed to create user");
                } else {
                    return new UserSuccess<>(201, "User successfully created", userEntity);
                }
            }
            case UpdateUser u -> {
                UserEntity userEntity = userService.update(u);
                if(userEntity == null) {
                    return new UserError<>(500, "Failed to update user");
                } else {
                    return new UserSuccess<>(200, "User successfully updated", userEntity);
                }
            }
            case DeleteUser d -> {
                userService.delete(d.id());

                if (userService.findById(d.id()) != null) {
                    return new UserError<>(500, "Failed to delete user");
                } else {
                    return new UserSuccess<>(200, "User successfully deleted", null);
                }
            }
        }
    }
}
