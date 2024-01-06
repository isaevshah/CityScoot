package kz.project.moped.presentation.controller;

import kz.project.moped.auth.security.JwtUserDetailsService;
import kz.project.moped.auth.security.jwt.JwtTokenProvider;
import kz.project.moped.auth.security.jwt.JwtUser;
import kz.project.moped.domain.model.RefreshToken;
import kz.project.moped.domain.model.User;
import kz.project.moped.presentation.dto.UserDTO;
import kz.project.moped.presentation.dto.request.AuthenticationRequestDto;
import kz.project.moped.presentation.dto.response.AuthenticationResponseDto;
import kz.project.moped.usecase.role.FetchRoleByNameUseCase;
import kz.project.moped.usecase.token.FindRefreshTokenByTokenUseCase;
import kz.project.moped.usecase.token.VerifyRefreshTokenUseCase;
import kz.project.moped.usecase.user.FindUserByUsernameUseCase;
import kz.project.moped.usecase.user.RegistrationUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RequiredArgsConstructor
@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final FindUserByUsernameUseCase findUserByUsernameUseCase;
    private final RegistrationUserUseCase registrationUserUseCase;
    private final FetchRoleByNameUseCase fetchRoleByNameUseCase;
    private final VerifyRefreshTokenUseCase verifyRefreshTokenUseCase;
    private final FindRefreshTokenByTokenUseCase findRefreshTokenByTokenUseCase;
    @PostMapping("/register")
    @SneakyThrows
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody UserDTO userDTO){
        try {
            String username = userDTO.getUsername();

            boolean isExist = findUserByUsernameUseCase.findUserByUsername(username).block() == null;

            if(!isExist){
                AuthenticationResponseDto response = new AuthenticationResponseDto();
                response.setError("User with username " + username + " already exists");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            User newUser = new User();
            newUser.setUsername(userDTO.getUsername());
            newUser.setFirstName(userDTO.getFirstname());
            newUser.setLastName(userDTO.getLastname());
            newUser.setBirthdate(userDTO.getBirthdate());
            newUser.setPassword(userDTO.getPassword());
            newUser.setRoles(Arrays.asList(fetchRoleByNameUseCase.findRoleByName("ROLE_USER")));
            registrationUserUseCase.registerUser(newUser).block();

            final JwtUser userDetails = jwtUserDetailsService.loadUserByUsername(username);
            final String token = jwtTokenProvider.generateToken(userDetails);
            AuthenticationResponseDto response = new AuthenticationResponseDto();
            response.setToken(token);
            response.setUser_info(userDetails);
            response.setUsername(username);
            RefreshToken refreshToken = jwtTokenProvider.createRefreshToken(userDetails);
            response.setRefreshToken(refreshToken.getToken());
            response.setError("User registered successfully");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            AuthenticationResponseDto response = new AuthenticationResponseDto();
            response.setError("Failed to register user");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
            final JwtUser userDetails = jwtUserDetailsService.loadUserByUsername(username);
            final String token = jwtTokenProvider.generateToken(userDetails);
            AuthenticationResponseDto response = new AuthenticationResponseDto();
            response.setToken(token);
            response.setUser_info(userDetails);
            response.setUsername(username);
//            RefreshToken refreshToken = jwtTokenProvider.createRefreshToken(userDetails);
//            response.setRefreshToken(refreshToken.getToken());
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
