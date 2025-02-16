package com.daniel.productHex.domain.ports;

import com.daniel.productHex.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductOutputPort {
    List<Product> findAll(); // Buscar todos los productos
    Optional<Product> findById(Long id); // Buscar un producto por ID
    Product save(Product product); // Guardar un producto
}