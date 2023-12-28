package kz.project.moped.presentation.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Getter
@Setter
public class AuthenticationResponseDto {
    private String token;
    private String refreshToken;
    private UserDetails user_info;
    private String username;
    private String error;
}
