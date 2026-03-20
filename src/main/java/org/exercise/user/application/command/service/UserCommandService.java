package org.exercise.user.application.command.service;

import org.exercise.user.application.command.exception.UserCommandException;
import org.exercise.user.application.command.exception.UserNotFoundException;
import org.exercise.user.application.command.model.CreateUser;
import org.exercise.user.application.command.model.UpdateUser;
import org.exercise.user.infrastructure.persistence.mapper.UserMapper;
import org.exercise.user.infrastructure.persistence.model.UserEntity;
import org.exercise.user.infrastructure.persistence.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCommandService {
    private static final Logger log = LoggerFactory.getLogger(UserCommandService.class);
    private final UserRepository userRepository;

    public UserCommandService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity create(CreateUser createUser) {
        if(Boolean.TRUE.equals(userRepository.existsByDni(createUser.dni().value()))) {
            throw new UserCommandException("Unable to create user - " +
                    "User with dni " + createUser.dni().value() + " already exists");
        }

        UserEntity entityUser = UserMapper.toEntity(createUser);
        userRepository.save(entityUser);

        log.info("User created with id: {}", entityUser.getId());

        return entityUser;
    }

    public UserEntity update(UpdateUser updateUser) {
        if(!userRepository.existsById(updateUser.id())) {
            throw new UserNotFoundException("Unable to update user - " +
                    "User with id " + updateUser.id() + " does not exist");
        }

        UserEntity entityUser = UserMapper.toEntity(updateUser);
        userRepository.save(entityUser);

        log.info("User updated with id: {}", entityUser.getId());

        return entityUser;
    }

    public void delete(UUID id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException("Unable to delete user - " +
                    "User with id " + id + " does not exist");
        }

        userRepository.deleteById(id);

        log.info("User deleted with id: {}", id);
    }
}
