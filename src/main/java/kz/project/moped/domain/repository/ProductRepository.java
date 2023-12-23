package kz.project.moped.domain.repository;

import kz.project.moped.domain.enums.ProductType;
import kz.project.moped.domain.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface ProductRepository {
    List<Product> getByProductType(ProductType productType);
    Product save(Product product);
    void deleteById(Long productId);
}
