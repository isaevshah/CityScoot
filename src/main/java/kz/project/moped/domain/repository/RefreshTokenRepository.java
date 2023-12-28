package kz.project.moped.domain.repository;

import kz.project.moped.domain.model.RefreshToken;

public interface RefreshTokenRepository {
    RefreshToken findByToken(String token);
    RefreshToken saveToken(RefreshToken refreshToken);
    void delete(RefreshToken refreshToken);
}
