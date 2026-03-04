package org.exercise.user.api.controller;

import org.exercise.user.application.command.CreateUser;
import org.exercise.user.application.command.DeleteUser;
import org.exercise.user.application.command.UpdateUser;
import org.exercise.user.application.usecase.UserCommandHandler;
import org.exercise.user.application.usecase.UserCommandResult;
import org.exercise.user.domain.dto.UserDTO;
import org.exercise.user.domain.model.DNI;
import org.exercise.user.domain.model.Email;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserCommandController {
    private final UserCommandHandler handler;

    public UserCommandController(UserCommandHandler handler) {
        this.handler = handler;
    }

    @PostMapping
    public UserCommandResult<?> createUser(@RequestBody UserDTO userDTO) {
        var command = new CreateUser(
                userDTO.firstName(),
                userDTO.lastName(),
                Email.of(userDTO.email()),
                DNI.of(userDTO.dni())
        );

        return handler.handle(command);
    }

    @PutMapping
    public UserCommandResult<?> updateUser(@RequestParam UUID id,
                             @RequestBody UserDTO userDTO) {
        var command = new UpdateUser(
                id,
                userDTO.firstName(),
                userDTO.lastName(),
                Email.of(userDTO.email()),
                DNI.of(userDTO.dni())
        );

        return handler.handle(command);
    }

    @DeleteMapping
    public UserCommandResult<?> deleteUser(@RequestParam UUID id) {
        var command = new DeleteUser(id);

        return handler.handle(command);
    }
}
