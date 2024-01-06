package kz.project.moped.infrastructure.persistense.postgresql.mapper;

import kz.project.moped.domain.model.Role;
import kz.project.moped.infrastructure.persistense.postgresql.entity.RoleEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {
    Role toDomain(RoleEntity role);
    RoleEntity toEntity(Role role);
}
