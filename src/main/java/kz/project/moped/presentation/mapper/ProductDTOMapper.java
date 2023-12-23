package kz.project.moped.presentation.mapper;

import kz.project.moped.domain.model.Product;
import kz.project.moped.presentation.dto.ProductDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDTOMapper {
    Product toDomain(ProductDTO productDTO);
    ProductDTO toDTO(Product product);
}
