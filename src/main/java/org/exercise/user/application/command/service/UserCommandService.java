package org.exercise.user.application.command.service;

import org.exercise.user.application.command.CreateUser;
import org.exercise.user.application.command.UpdateUser;
import org.exercise.user.domain.dto.UserDTO;
import org.exercise.user.infrastructure.persistence.mapper.UserMapper;
import org.exercise.user.infrastructure.persistence.model.UserEntity;
import org.exercise.user.infrastructure.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCommandService {
    private final UserRepository userRepository;

    public UserCommandService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity create(CreateUser createUser) {
        UserEntity entityUser = UserMapper.toEntity(createUser);
        userRepository.save(entityUser);

        return entityUser;
    }

    public UserEntity update(UpdateUser updateUser) {
        UserEntity entityUser = UserMapper.toEntity(updateUser);
        userRepository.save(entityUser);

        return entityUser;
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public UserDTO findById(UUID id) {
        return userRepository.findById(id)
                .map(UserMapper::toDomain)
                .orElse(null);
    }
}
