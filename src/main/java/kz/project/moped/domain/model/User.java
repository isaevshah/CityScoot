package kz.project.moped.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private List<Role> roles;

    public User() {

    }
}
