package kz.project.moped.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class RefreshToken {
    private Long id;
    private String username;
    private String token;
    private Date expiryDate;
 }
