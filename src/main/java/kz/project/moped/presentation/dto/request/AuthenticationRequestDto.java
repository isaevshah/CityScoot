package kz.project.moped.presentation.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
