package kz.project.moped.infrastructure.persistense.postgresql.mapper;

import kz.project.moped.domain.model.Product;
import kz.project.moped.infrastructure.persistense.postgresql.entity.ProductEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    Product toDomain(ProductEntity product);

    ProductEntity toEntity(Product product);
}
