package com.daniel.productHex.application.dtos;

import java.util.List;

public class ProductImportResponse {
    private List<ProductResponse> importedProducts;
    private List<ProductRequest> rejectedProducts;

    public ProductImportResponse(List<ProductResponse> importedProducts, List<ProductRequest> rejectedProducts) {
        this.importedProducts = importedProducts;
        this.rejectedProducts = rejectedProducts;
    }

    public List<ProductResponse> getImportedProducts() {
        return importedProducts;
    }

    public void setImportedProducts(List<ProductResponse> importedProducts) {
        this.importedProducts = importedProducts;
    }

    public List<ProductRequest> getRejectedProducts() {
        return rejectedProducts;
    }

    public void setRejectedProducts(List<ProductRequest> rejectedProducts) {
        this.rejectedProducts = rejectedProducts;
    }
}
