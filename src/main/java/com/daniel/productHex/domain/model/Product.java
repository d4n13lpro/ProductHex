package com.daniel.productHex.domain.model;

import com.daniel.productHex.application.dtos.ProductResponse;

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
    private final Long categoryId;
    private final Long brandId;
    private final Long supplierId;

    /**
     * Constructor de la entidad Product.
     *
     * @param id          Identificador único del producto.
     * @param name        Nombre del producto. No puede ser nulo o vacío.
     * @param description Descripción del producto (puede ser nula, se asignará cadena vacía).
     * @param price       Precio del producto. No puede ser nulo y debe ser mayor o igual a cero.
     * @param quantity    Cantidad disponible del producto. Debe ser mayor o igual a cero.
     * @param categoryId  ID de la categoría a la que pertenece el producto (no puede ser nulo).
     * @param brandId     ID de la marca del producto (no puede ser nulo).
     * @param supplierId  ID del proveedor del producto (no puede ser nulo).
     * @throws IllegalArgumentException Si los datos proporcionados no son válidos.
     */
    public Product(Long id, String name, String description, BigDecimal price, int quantity, Long categoryId, Long brandId, Long supplierId) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
        if (brandId == null) {
            throw new IllegalArgumentException("Brand ID cannot be null");
        }
        if (supplierId == null) {
            throw new IllegalArgumentException("Supplier ID cannot be null");
        }

        this.id = id;
        this.name = name;
        this.description = (description != null) ? description : ""; // Manejo de null en descripción
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.supplierId = supplierId;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public Long getCategoryId() { return categoryId; }
    public Long getBrandId() { return brandId; }
    public Long getSupplierId() { return supplierId; }


    /**
     * Valida si el producto es válido.
     * Un producto es válido si:
     * - El nombre no es nulo ni está vacío.
     * - El precio no es nulo y es mayor o igual a cero.
     * - La cantidad es mayor o igual a cero.
     * - Los IDs de categoría, marca y proveedor no son nulos.
     *
     * @return true si el producto es válido, false en caso contrario.
     */
    public boolean isValid() {
        return name != null && !name.isEmpty() &&
                price != null && price.compareTo(BigDecimal.ZERO) >= 0 &&
                quantity >= 0 &&
                categoryId != null &&
                brandId != null &&
                supplierId != null;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                ", supplierId=" + supplierId +
                '}';
    }


}
