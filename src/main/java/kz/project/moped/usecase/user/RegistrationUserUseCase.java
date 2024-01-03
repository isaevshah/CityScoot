package kz.project.moped.usecase.user;

import kz.project.moped.domain.model.Product;
import kz.project.moped.domain.model.User;
import kz.project.moped.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RegistrationUserUseCase {
    private final UserRepository userRepository;
    public Mono<User> registerUser(User user){
        return Mono.just(userRepository.save(user));
    }
}
