package kz.project.moped.presentation.dto;

import kz.project.moped.domain.model.Role;
import lombok.*;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private List<RoleDTO> roles;
}
