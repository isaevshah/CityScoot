package kz.project.moped.domain.repository;

import kz.project.moped.domain.model.User;

public interface UserRepository {
    User findByUsername(String username);
    User save(User user);
}
