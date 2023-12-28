package kz.project.moped.infrastructure.persistense.postgresql.adapter;

import kz.project.moped.domain.model.RefreshToken;
import kz.project.moped.domain.repository.RefreshTokenRepository;
import kz.project.moped.infrastructure.persistense.postgresql.entity.RefreshTokenEntity;
import kz.project.moped.infrastructure.persistense.postgresql.repository.JpaRefreshTokenRepository;
import kz.project.moped.presentation.mapper.RefreshTokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {
    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public RefreshToken findByToken(String token) {
        return jpaRefreshTokenRepository.findByToken(token).map(refreshTokenMapper::toDpomain).orElse(null);
    }

    @Override
    public RefreshToken saveToken(RefreshToken refreshToken) {
        RefreshTokenEntity savedRefreshToken = jpaRefreshTokenRepository.save(refreshTokenMapper.toEntity(refreshToken));
        return refreshTokenMapper.toDpomain(savedRefreshToken);
    }

    @Override
    public void delete(RefreshToken refreshToken) {
        jpaRefreshTokenRepository.delete(refreshTokenMapper.toEntity(refreshToken));
    }
}
