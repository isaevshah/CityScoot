package kz.project.moped.presentation.dto;

import kz.project.moped.domain.model.Role;
import lombok.*;

import java.util.Date;
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
    private String firstname;
    private String lastname;
    private Date birthdate;
    private List<Role> roles;
}
