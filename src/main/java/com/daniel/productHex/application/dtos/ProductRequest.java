package com.daniel.productHex.application.dtos;

import java.math.BigDecimal;

public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private Long categoryId;
    private Long brandId;
    private Long supplierId;

    public ProductRequest() {
        this.name = "";
        this.description = "";
        this.price = BigDecimal.ZERO;
        this.quantity = 0;
        this.categoryId = 0L;
        this.brandId = 0L;
        this.supplierId = 0L;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void validate() {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("El nombre es obligatorio");
        if (description == null || description.trim().isEmpty()) throw new IllegalArgumentException("La descripción es obligatoria");
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("El precio debe ser mayor o igual a 0");
        if (quantity < 0) throw new IllegalArgumentException("La cantidad no puede ser negativa");
        if (categoryId == null || categoryId <= 0) throw new IllegalArgumentException("Debe especificar una categoría válida");
        if (brandId == null || brandId <= 0) throw new IllegalArgumentException("Debe especificar una marca válida");
        if (supplierId == null || supplierId <= 0) throw new IllegalArgumentException("Debe especificar un proveedor válido");
    }
}
