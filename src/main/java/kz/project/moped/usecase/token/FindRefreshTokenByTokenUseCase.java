package kz.project.moped.usecase.token;

import kz.project.moped.domain.model.RefreshToken;
import kz.project.moped.domain.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindRefreshTokenByTokenUseCase {
    private final RefreshTokenRepository refreshTokenRepository;

    public Optional<RefreshToken> findRefreshTokenByToken(String token) {
        return Optional.of(refreshTokenRepository.findByToken(token));
    }
}
