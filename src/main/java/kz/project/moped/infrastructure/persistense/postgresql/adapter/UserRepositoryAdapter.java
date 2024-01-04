package kz.project.moped.infrastructure.persistense.postgresql.adapter;

import kz.project.moped.domain.model.User;
import kz.project.moped.domain.repository.UserRepository;
import kz.project.moped.infrastructure.persistense.postgresql.entity.UserEntity;
import kz.project.moped.infrastructure.persistense.postgresql.mapper.UserMapper;
import kz.project.moped.infrastructure.persistense.postgresql.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        UserEntity findUser = jpaUserRepository.findByUsername(username);
        return findUser != null ? userMapper.toDomain(findUser) : null;
    }

    @Override
    public User save(User user) {
        UserEntity saveUser = jpaUserRepository.save(userMapper.toEntity(user));
        return userMapper.toDomain(saveUser);
    }
}
