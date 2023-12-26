package kz.project.moped.infrastructure.persistense.postgresql.repository;

import kz.project.moped.domain.model.User;
import kz.project.moped.infrastructure.persistense.postgresql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<User> findByEmail(String email);
}
