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
public class BaseHierarchicalReferenceEntity extends BaseReferenceEntity{
    @Column(name = "level_")
    private Integer level;
}
