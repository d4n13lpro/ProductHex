package com.daniel.productHex.domain.service;

import com.daniel.productHex.domain.model.Product;
import com.daniel.productHex.domain.ports.ProductOutputPort;
import com.daniel.productHex.domain.exceptions.ProductNotFoundException;
import com.daniel.productHex.domain.exceptions.InvalidProductException;
import org.springframework.stereotype.Service;

import java.util.*;

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

    // Crear un producto evitando duplicados
    public Product createProduct(Product product) {
        if (!product.isValid()) {
            throw new InvalidProductException("Product is not valid: " + product);
        }

        // Validar si ya existe un producto con el mismo nombre, marca y categoría
        Optional<Product> existingProduct = productOutputPort.findByNameAndBrandIdAndCategoryId(
                product.getName(), product.getBrandId(), product.getCategoryId()
        );

        if (existingProduct.isPresent()) {
            throw new InvalidProductException("El producto ya existe en la base de datos.");
        }

        return productOutputPort.save(product);
    }
    public Map<String, Object> createProducts(List<Product> products) {
        List<Product> insertedProducts = new ArrayList<>();
        List<Map<String, Object>> failedProducts = new ArrayList<>();

        for (Product product : products) {
            try {
                if (!product.isValid()) {
                    throw new InvalidProductException("Producto no válido: " + product);
                }

                Optional<Product> existingProduct = productOutputPort.findByNameAndBrandIdAndCategoryId(
                        product.getName(), product.getBrandId(), product.getCategoryId()
                );

                if (existingProduct.isPresent()) {
                    throw new InvalidProductException("El producto ya existe en la base de datos.");
                }

                Product savedProduct = productOutputPort.save(product);
                insertedProducts.add(savedProduct);
            } catch (Exception e) {
                Map<String, Object> errorDetail = new HashMap<>();
                errorDetail.put("product", product);
                errorDetail.put("error", e.getMessage());
                failedProducts.add(errorDetail);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("insertedProducts", insertedProducts);
        response.put("failedProducts", failedProducts);

        return response;
    }
}
