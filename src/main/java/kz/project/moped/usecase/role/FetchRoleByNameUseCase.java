package kz.project.moped.usecase.role;

import kz.project.moped.domain.model.Role;
import kz.project.moped.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FetchRoleByNameUseCase {
    private final RoleRepository roleRepository;

    public Role findRoleByName(String name){
        return roleRepository.findByName(name);
    }
}
