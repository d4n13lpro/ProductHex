package com.daniel.productHex.application.ports;

import com.daniel.productHex.application.dtos.ProductRequest;
import com.daniel.productHex.domain.model.Product;

import java.util.List;

public interface ProductImportPort {
    // Crear un producto
    Product createProduct(ProductRequest productRequest);

    // Buscar todos los productos
    List<Product> getAllProducts();

    // Buscar un producto por ID
    Product getProductById(Long id);
}