package com.br.api.wishlist.domain.model;


import java.util.Objects;

public class Product {
    private final String productId;

    public Product(String productId) {
        if (productId == null || productId.length() != 24) {
            throw new IllegalArgumentException("Invalid productId. Must be 24 characters (MongoDB ObjectId format).");
        }
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + getProductId() +
                '}';
    }
}



