package com.daniel.productHex.domain.model;

import java.math.BigDecimal;

/**
 * Representa la entidad Product en el dominio.
 * Contiene los atributos y la lógica de negocio relacionada con un producto.
 */
public class Product {
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int quantity;

    /**
     * Constructor de la entidad Product.
     *
     * @param id          Identificador único del producto.
     * @param name        Nombre del producto. No puede ser nulo o vacío.
     * @param description Descripción del producto.
     * @param price       Precio del producto. No puede ser nulo y debe ser mayor o igual a cero.
     * @param quantity    Cantidad disponible del producto. Debe ser mayor o igual a cero.
     * @throws IllegalArgumentException Si los datos proporcionados no son válidos.
     */
    public Product(Long id, String name, String description, BigDecimal price, int quantity) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Valida si el producto es válido.
     * Un producto es válido si:
     * - El nombre no es nulo ni está vacío.
     * - El precio no es nulo y es mayor o igual a cero.
     * - La cantidad es mayor o igual a cero.
     *
     * @return true si el producto es válido, false en caso contrario.
     */
    public boolean isValid() {
        return name != null && !name.isEmpty() && price != null && price.compareTo(BigDecimal.ZERO) >= 0 && quantity >= 0;
    }

    /**
     * Representación en cadena de la entidad Product.
     *
     * @return Una cadena que representa el producto.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}