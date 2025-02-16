package com.daniel.productHex.infraestructure.rest.mapper;

import com.daniel.productHex.application.dtos.ProductRequest;
import com.daniel.productHex.application.dtos.ProductResponse;
import com.daniel.productHex.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toDomain(ProductRequest productRequest) {
        if (productRequest == null) {
            throw new IllegalArgumentException("ProductRequest cannot be null");
        }
        return new Product(
                null, // El ID se generará automáticamente en la base de datos
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.getQuantity()
        );
    }

    public ProductResponse toResponse(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }
}