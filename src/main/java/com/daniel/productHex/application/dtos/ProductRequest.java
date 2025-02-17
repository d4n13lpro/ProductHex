package com.daniel.productHex.application.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0")
    private BigDecimal price;

    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private int quantity;

    @NotNull(message = "Debe especificar una categoría válida")
    @Positive(message = "La categoría debe ser positiva")
    private Long categoryId;

    @NotNull(message = "Debe especificar una marca válida")
    @Positive(message = "La marca debe ser positiva")
    private Long brandId;

    @NotNull(message = "Debe especificar un proveedor válido")
    @Positive(message = "El proveedor debe ser positivo")
    private Long supplierId;

    // Constructor vacío para inicialización
    public ProductRequest() {
        this.name = "";
        this.description = "";
        this.price = BigDecimal.ZERO;
        this.quantity = 0;
        this.categoryId = 0L;
        this.brandId = 0L;
        this.supplierId = 0L;
    }

    // Getters y Setters
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
}
