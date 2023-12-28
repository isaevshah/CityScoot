package kz.project.moped.auth.security;

import kz.project.moped.auth.security.jwt.JwtUser;
import kz.project.moped.auth.security.jwt.JwtUserFactory;
import kz.project.moped.domain.model.User;
import kz.project.moped.usecase.user.FindUserByUsernameUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final JwtUserFactory jwtUserFactory;
    private final FindUserByUsernameUseCase findUserByUsernameUseCase;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsernameUseCase.findUserByUsername(username).block();
        if (user == null) {
            log.warn("User with username:{} not found", username);
            throw new UsernameNotFoundException("User with username" + username + " not found");
        }
        JwtUser jwtUser = jwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully  loaded", username);
        return jwtUser;
    }
}
