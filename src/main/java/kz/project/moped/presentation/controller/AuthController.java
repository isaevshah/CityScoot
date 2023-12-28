package kz.project.moped.presentation.controller;

import kz.project.moped.auth.security.JwtUserDetailsService;
import kz.project.moped.auth.security.jwt.JwtTokenProvider;
import kz.project.moped.auth.security.jwt.JwtUser;
import kz.project.moped.domain.model.RefreshToken;
import kz.project.moped.infrastructure.persistense.postgresql.exception.TokenRefreshException;
import kz.project.moped.presentation.dto.request.AuthenticationRequestDto;
import kz.project.moped.presentation.dto.request.TokenRefreshRequest;
import kz.project.moped.presentation.dto.response.AuthenticationResponseDto;
import kz.project.moped.usecase.token.FindRefreshTokenByTokenUseCase;
import kz.project.moped.usecase.token.VerifyRefreshTokenUseCase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final VerifyRefreshTokenUseCase verifyRefreshTokenUseCase;
    private final FindRefreshTokenByTokenUseCase findRefreshTokenByTokenUseCase;
    @PostMapping("/login")
    @SneakyThrows
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            if (username.isEmpty()) {
                AuthenticationResponseDto response = new AuthenticationResponseDto();
                response.setError("username is empty");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
            final JwtUser userDetails = jwtUserDetailsService.loadUserByUsername(username.toUpperCase());
            final String token = jwtTokenProvider.generateToken(userDetails);
            AuthenticationResponseDto response = new AuthenticationResponseDto();
            response.setToken(token);
            response.setUser_info(userDetails);
            response.setUsername(username);
            RefreshToken refreshToken = jwtTokenProvider.createRefreshToken(userDetails);
            response.setRefreshToken(refreshToken.getToken());
            log.info("AUTH: User " + username + " was auth");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AuthenticationException e) {
            AuthenticationResponseDto response = new AuthenticationResponseDto();
            response.setError("Invalid username or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

//    @PostMapping("/refresh")
//    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
//        String requestRefreshToken = request.getRefreshToken();
//        return findRefreshTokenByTokenUseCase.findRefreshTokenByToken(requestRefreshToken)
//                .map(verifyRefreshTokenUseCase::verifyExpiration)
//                .map(refreshToken -> {
//                    JwtUser userDetails = jwtUserDetailsService.loadUserByUsername(refreshToken.getUsername().toUpperCase());
//                    String token = jwtTokenProvider.generateToken(userDetails);
//                    AuthenticationResponseDto response = new AuthenticationResponseDto();
//                    response.setToken(token);
//                    RefreshToken newRefreshToken = jwtTokenProvider.createRefreshToken(userDetails);
//                    response.setRefreshToken(newRefreshToken.getToken());
//                    response.setUser_info(userDetails);
//                    response.setUsername(refreshToken.getUsername());
//                    return ResponseEntity.ok(response);
//                }).orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token is not in database"));
//
//    }
}
