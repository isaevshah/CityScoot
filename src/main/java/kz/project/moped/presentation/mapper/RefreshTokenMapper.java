package kz.project.moped.presentation.mapper;

import kz.project.moped.domain.model.RefreshToken;
import kz.project.moped.infrastructure.persistense.postgresql.entity.RefreshTokenEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RefreshTokenMapper {
    RefreshToken toDpomain(RefreshTokenEntity refreshTokenEntity);
    RefreshTokenEntity toEntity(RefreshToken refreshToken);
}
