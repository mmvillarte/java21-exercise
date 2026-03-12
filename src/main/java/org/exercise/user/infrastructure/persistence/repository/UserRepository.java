package org.exercise.user.infrastructure.persistence.repository;

import org.exercise.user.infrastructure.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Boolean existsByDni(String dni);
}
