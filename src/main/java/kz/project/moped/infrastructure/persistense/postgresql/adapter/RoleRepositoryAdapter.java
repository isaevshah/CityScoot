package kz.project.moped.infrastructure.persistense.postgresql.adapter;

import kz.project.moped.domain.model.Role;
import kz.project.moped.domain.repository.RoleRepository;
import kz.project.moped.infrastructure.persistense.postgresql.mapper.RoleMapper;
import kz.project.moped.infrastructure.persistense.postgresql.repository.JpaRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepository {
    private final JpaRoleRepository jpaRoleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Role findByName(String name) {
        return roleMapper.toDomain(jpaRoleRepository.findByName(name));
    }
}
