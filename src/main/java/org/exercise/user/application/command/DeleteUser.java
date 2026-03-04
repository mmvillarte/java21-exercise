package org.exercise.user.application.command;

import java.util.UUID;

public record DeleteUser(UUID id)
        implements UserCommand {
}
