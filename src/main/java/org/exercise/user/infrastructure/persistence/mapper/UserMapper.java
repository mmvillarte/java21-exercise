package org.exercise.user.infrastructure.persistence.mapper;

import org.exercise.user.application.command.CreateUser;
import org.exercise.user.application.command.UpdateUser;
import org.exercise.user.domain.dto.UserDTO;
import org.exercise.user.infrastructure.persistence.model.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(CreateUser createUser) {
        return new UserEntity(
                createUser.firstName(),
                createUser.lastName(),
                createUser.email().value(),
                createUser.dni().value()
        );
    }

    public static UserEntity toEntity(UpdateUser updateUser) {
        return new UserEntity(
                updateUser.id(),
                updateUser.firstName(),
                updateUser.lastName(),
                updateUser.email().value(),
                updateUser.dni().value()
        );
    }

    public static UserDTO toDomain(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getDni()
        );
    }
}
