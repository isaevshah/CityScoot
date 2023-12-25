package kz.project.moped.infrastructure.persistense.postgresql.entity;
import jakarta.persistence.*;
import kz.project.moped.domain.enums.RoleType;
import kz.project.moped.infrastructure.persistense.postgresql.entity.base.BaseAuditEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "roles")
@Entity
public class RoleEntity extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
