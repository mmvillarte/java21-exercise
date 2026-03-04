package org.exercise.user.application.command;

public sealed interface UserCommand permits CreateUser, UpdateUser, DeleteUser {
}
