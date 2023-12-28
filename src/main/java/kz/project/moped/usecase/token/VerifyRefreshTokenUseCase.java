package kz.project.moped.usecase.token;

import kz.project.moped.domain.model.RefreshToken;
import kz.project.moped.domain.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class VerifyRefreshTokenUseCase {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().before(new Date())) {
            refreshTokenRepository.delete(token);
            //throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }
}
