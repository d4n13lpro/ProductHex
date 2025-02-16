package com.daniel.productHex.infraestructure.rest.controller;

import com.daniel.productHex.application.dtos.ProductRequest;
import com.daniel.productHex.application.dtos.ProductResponse;
import com.daniel.productHex.application.ports.ProductImportPort;
import com.daniel.productHex.domain.model.Product;
import com.daniel.productHex.infraestructure.rest.mapper.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Valid
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductImportPort productImportPort;
    private final ProductMapper productMapper;

    public ProductController(ProductImportPort productImportPort, ProductMapper productMapper) {
        this.productImportPort = productImportPort;
        this.productMapper = productMapper;
    }

    // Método para crear un solo producto
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product createdProduct = productImportPort.createProduct(productRequest);
        ProductResponse response = productMapper.toResponse(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Método para crear múltiples productos
    @PostMapping("/batch")
    public ResponseEntity<List<ProductResponse>> createProducts(@Valid @RequestBody List<ProductRequest> productRequests) {
        List<ProductResponse> responses = productRequests.stream()
                .map(productImportPort::createProduct)
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> products = productImportPort.getAllProducts();
        List<ProductResponse> responseList = products.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        Product product = productImportPort.getProductById(id);
        ProductResponse response = productMapper.toResponse(product);
        return ResponseEntity.ok(response);
    }
}