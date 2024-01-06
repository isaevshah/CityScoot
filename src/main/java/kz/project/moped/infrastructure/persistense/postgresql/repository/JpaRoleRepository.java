package kz.project.moped.infrastructure.persistense.postgresql.repository;

import kz.project.moped.infrastructure.persistense.postgresql.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
