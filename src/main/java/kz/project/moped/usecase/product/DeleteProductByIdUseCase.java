package kz.project.moped.usecase.product;

import kz.project.moped.domain.model.Product;
import kz.project.moped.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeleteProductByIdUseCase {
    private final ProductRepository productRepository;
     public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
