package kz.project.moped.infrastructure.persistense.postgresql.adapter;

import kz.project.moped.domain.enums.ProductType;
import kz.project.moped.domain.model.Product;
import kz.project.moped.domain.model.User;
import kz.project.moped.domain.repository.ProductRepository;
import kz.project.moped.infrastructure.persistense.postgresql.mapper.ProductMapper;
import kz.project.moped.infrastructure.persistense.postgresql.entity.ProductEntity;
import kz.project.moped.infrastructure.persistense.postgresql.repository.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper productMapper;

    @Override
    public List<Product> getByProductType(ProductType productType) {
        List<ProductEntity> productList = jpaProductRepository.findProductEntitiesByProductType(productType);
        return productList.stream().map(productMapper::toDomain).toList();
    }

    @Override
    public Product save(Product product) {
        ProductEntity saveProductEntity = jpaProductRepository.save(productMapper.toEntity(product));
        return productMapper.toDomain(saveProductEntity);
    }

    @Override
    public void deleteById(Long productId) {
        jpaProductRepository.deleteById(productId);
    }
}
