package com.daniel.productHex.domain.service;

import com.daniel.productHex.domain.model.Product;
import com.daniel.productHex.domain.ports.ProductOutputPort;
import com.daniel.productHex.domain.exceptions.ProductNotFoundException;
import com.daniel.productHex.domain.exceptions.InvalidProductException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductOutputPort productOutputPort;

    public ProductService(ProductOutputPort productOutputPort) {
        this.productOutputPort = productOutputPort;
    }

    // Buscar todos los productos
    public List<Product> getAllProducts() {
        return productOutputPort.findAll();
    }

    // Buscar un producto por ID
    public Product getProductById(Long id) {
        return productOutputPort.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    // Crear un producto
    public Product createProduct(Product product) {
        if (!product.isValid()) {
            throw new InvalidProductException("Product is not valid: " + product);
        }
        return productOutputPort.save(product);
    }
}