package com.daniel.productHex.infraestructure.rest.mapper;

import com.daniel.productHex.application.dtos.ProductRequest;
import com.daniel.productHex.application.dtos.ProductResponse;
import com.daniel.productHex.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductMapper {

    public Product toDomain(ProductRequest productRequest) {
        Objects.requireNonNull(productRequest, "ProductRequest cannot be null");

        return new Product(
                null, // El ID se generará automáticamente en la base de datos
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.getQuantity(),
                productRequest.getCategoryId(),
                productRequest.getBrandId(),
                productRequest.getSupplierId()
        );
    }

    public ProductResponse toResponse(Product product) {
        Objects.requireNonNull(product, "Product cannot be null");

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategoryId(),
                product.getBrandId(),
                product.getSupplierId()
        );
    }
}
