package kz.project.moped.domain.repository;

import kz.project.moped.domain.model.Role;

public interface RoleRepository {
    Role findByName(String name);
}
