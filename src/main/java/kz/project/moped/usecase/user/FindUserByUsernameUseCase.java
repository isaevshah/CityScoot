package kz.project.moped.usecase.user;

import kz.project.moped.domain.model.User;
import kz.project.moped.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FindUserByUsernameUseCase {
    private final UserRepository userRepository;
    public Mono<User> findUserByUsername(String username){
        return Mono.just(userRepository.findByUsername(username));
    }
}
