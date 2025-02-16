package com.daniel.productHex.application.service;

import com.daniel.productHex.application.dtos.ProductRequest;
import com.daniel.productHex.application.ports.ProductImportPort;
import com.daniel.productHex.domain.model.Product;
import com.daniel.productHex.domain.service.ProductService;
import com.daniel.productHex.infraestructure.rest.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductApplicationService implements ProductImportPort {

    private final ProductService productService; // Servicio de dominio
    private final ProductMapper productMapper;   // Mapper para conversión de DTOs

    public ProductApplicationService(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        // Convertir ProductRequest (DTO) a Product (Dominio)
        Product product = productMapper.toDomain(productRequest);

        // Validar y guardar el producto en el dominio
        return productService.createProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        // Obtener todos los productos del dominio
        return productService.getAllProducts();
    }

    @Override
    public Product getProductById(Long id) {
        // Obtener un producto por ID del dominio
        return productService.getProductById(id);
    }
}