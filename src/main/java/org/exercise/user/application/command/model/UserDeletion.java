package org.exercise.user.application.command.model;

import java.util.UUID;

public record UserDeletion(UUID id)
        implements UserCommand {
}
