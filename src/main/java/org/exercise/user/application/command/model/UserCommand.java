package org.exercise.user.application.command.model;

public sealed interface UserCommand permits UserCreator, UserUpdate, UserDeletion {
}
