package kz.project.moped.infrastructure.persistense.postgresql.adapter;

import kz.project.moped.domain.model.User;
import kz.project.moped.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    @Override
    public User findByUsername(String username) {
        return null;
    }
}
