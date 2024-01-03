package kz.project.moped.auth.security.jwt;

import kz.project.moped.domain.model.Role;
import kz.project.moped.domain.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserFactory {
    public JwtUserFactory(){
    }
    public JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                mapToGrantedAuthority(user.getRoles()),
                mapToRoleNames(new ArrayList<>(user.getRoles()))
        );
    }
    private static List<GrantedAuthority> mapToGrantedAuthority(List<Role> userRoleEntities){
        return userRoleEntities.stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode()))
                .collect(Collectors.toList());
    }
    private static List<String> mapToRoleNames(List<Role> userRoles){
        return userRoles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
}
