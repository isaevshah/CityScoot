package kz.project.moped.auth.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import kz.project.moped.domain.model.RefreshToken;
import kz.project.moped.usecase.token.CreateRefreshTokenUseCase;
import kz.project.moped.usecase.user.FindUserByUsernameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final FindUserByUsernameUseCase findUserByUsernameUseCase;
    @Value("${secret}")
    private String secret;

    @Value("${validityInMinutes}")
    private Long validityInMinutes;

    @Value("${refresh.token.duration.minutes}")
    private Long refreshTokenDurationMinutes;
    private final byte[] secureKey = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secureKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(JwtUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userDetails.getId());
        claims.put("username", userDetails.getUsername());
        claims.put("authorities", userDetails.getAuthorities());
        claims.put("rolename", userDetails.getRoleNames());
        return createToken(claims, userDetails.getUsername());
    }


    public RefreshToken createRefreshToken(JwtUser user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .username(user.getUsername())
                .expiryDate(new Date(new Date().getTime() + TimeUnit.MINUTES.toMillis(refreshTokenDurationMinutes)))
                .build();
        return createRefreshTokenUseCase.createRefreshToken(refreshToken);
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    private String createToken(Map<String, Object> claims, String subject) {
        Date tokenValidity = new Date(new Date().getTime() + TimeUnit.MINUTES.toMillis(validityInMinutes));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer("moped")
                .setIssuedAt(new Date())
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secureKey)
                .compact();
    }
}
