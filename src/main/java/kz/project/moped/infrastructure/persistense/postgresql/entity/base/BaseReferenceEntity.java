package kz.project.moped.infrastructure.persistense.postgresql.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseReferenceEntity extends BaseAuditEntity{
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
}
