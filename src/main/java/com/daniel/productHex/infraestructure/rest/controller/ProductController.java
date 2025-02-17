package com.daniel.productHex.infraestructure.rest.controller;

import com.daniel.productHex.application.dtos.ProductImportResponse;
import com.daniel.productHex.application.dtos.ProductRequest;
import com.daniel.productHex.application.dtos.ProductResponse;
import com.daniel.productHex.application.ports.ProductImportPort;
import com.daniel.productHex.domain.model.Product;
import com.daniel.productHex.infraestructure.rest.mapper.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    // Crear un solo producto
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product createdProduct = productImportPort.createProduct(productRequest);
        ProductResponse response = productMapper.toResponse(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Crear múltiples productos
    @PostMapping("/batch")
    public ResponseEntity<ProductImportResponse> createProducts(@Valid @RequestBody List<ProductRequest> productRequests) {
        List<ProductResponse> successfulProducts = new ArrayList<>();
        List<ProductRequest> rejectedProducts = new ArrayList<>();

        for (ProductRequest productRequest : productRequests) {
            try {
                // Intentar crear el producto
                Product createdProduct = productImportPort.createProduct(productRequest);
                ProductResponse response = productMapper.toResponse(createdProduct);
                successfulProducts.add(response);
            } catch (Exception e) {
                // Si ocurre un error, agregar el producto a la lista de rechazados
                rejectedProducts.add(productRequest);
            }
        }

        // Crear la respuesta con productos exitosos y rechazados
        ProductImportResponse response = new ProductImportResponse(successfulProducts, rejectedProducts);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> products = productImportPort.getAllProducts();
        List<ProductResponse> responseList = products.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    // Obtener un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        Product product = productImportPort.getProductById(id);
        ProductResponse response = productMapper.toResponse(product);
        return ResponseEntity.ok(response);
    }
}