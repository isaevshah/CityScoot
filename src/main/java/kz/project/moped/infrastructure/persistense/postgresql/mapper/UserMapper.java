package kz.project.moped.infrastructure.persistense.postgresql.mapper;

import kz.project.moped.domain.model.User;
import kz.project.moped.infrastructure.persistense.postgresql.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User toDomain(UserEntity user);
    UserEntity toEntity(User user);
}
