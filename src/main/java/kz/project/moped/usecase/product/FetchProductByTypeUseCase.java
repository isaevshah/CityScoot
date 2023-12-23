package kz.project.moped.usecase.product;

import kz.project.moped.domain.enums.ProductType;
import kz.project.moped.domain.model.Product;
import kz.project.moped.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FetchProductByTypeUseCase {
    private final ProductRepository productRepository;

    public List<Product> fetchProductByType(ProductType productType){
        return productRepository.getByProductType(productType);
    }
}
