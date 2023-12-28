package kz.project.moped.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Role {
    private Long id;
    private String code;
    private String name;
}
