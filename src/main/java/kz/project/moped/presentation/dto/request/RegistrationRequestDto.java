package kz.project.moped.presentation.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class RegistrationRequestDto {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Date birthdate;
}
