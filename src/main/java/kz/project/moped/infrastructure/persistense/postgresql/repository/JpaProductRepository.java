package kz.project.moped.infrastructure.persistense.postgresql.repository;

import kz.project.moped.domain.enums.ProductType;
import kz.project.moped.domain.model.Product;
import kz.project.moped.infrastructure.persistense.postgresql.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findProductEntitiesByProductType(ProductType productType);
    void deleteById(Long productId);
}
