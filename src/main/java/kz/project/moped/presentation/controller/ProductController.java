package kz.project.moped.presentation.controller;

import kz.project.moped.domain.enums.ProductType;
import kz.project.moped.domain.model.Product;
import kz.project.moped.presentation.dto.ProductDTO;
import kz.project.moped.presentation.mapper.ProductDTOMapper;
import kz.project.moped.usecase.product.CreateProductUseCase;
import kz.project.moped.usecase.product.DeleteProductByIdUseCase;
import kz.project.moped.usecase.product.FetchProductByTypeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final FetchProductByTypeUseCase fetchProductByTypeUseCase;
    private final DeleteProductByIdUseCase deleteProductByIdUseCase;
    private final ProductDTOMapper productDTOMapper;

    @PostMapping("/save_product")
    Mono<ProductDTO> createProduct(@RequestBody ProductDTO productDTO, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        Mono<ProductDTO> savedProductDTO = createProductUseCase.createProduct(productDTOMapper.toDomain(productDTO)).map(productDTOMapper::toDTO);
        return savedProductDTO;
    }
    @GetMapping("/fetch_product_by_type")
    List<ProductDTO> fetchProductByType(@RequestParam("productType") ProductType productType){
        List<Product> productList = fetchProductByTypeUseCase.fetchProductByType(productType);
        return productList.stream()
                .map(productDTOMapper::toDTO).collect(Collectors.toList());
    }
    @DeleteMapping("/delete_product_by_id/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Long productId){
        deleteProductByIdUseCase.deleteProductById(productId);
        return ResponseEntity.ok("Deleted successfully");
    }
}
