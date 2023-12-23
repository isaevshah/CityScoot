package kz.project.moped.usecase.product;

import kz.project.moped.domain.model.Product;
import kz.project.moped.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateProductUseCase {
    private final ProductRepository productRepository;
    public Mono<Product> createProduct(Product product){
        return Mono.just(productRepository.save(product));
    }
}
