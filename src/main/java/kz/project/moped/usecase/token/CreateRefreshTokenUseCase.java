package kz.project.moped.usecase.token;

import kz.project.moped.domain.model.RefreshToken;
import kz.project.moped.domain.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateRefreshTokenUseCase {
    private final RefreshTokenRepository refreshTokenRepository;
    public RefreshToken createRefreshToken(RefreshToken refreshToken){
        return  refreshTokenRepository.saveToken(refreshToken);
    }
}
