package com.br.api.wishlist.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {
    private final String clientId;
    private final List<Product> products = new ArrayList<>();

    public Wishlist(String clientId) {
        this.clientId = clientId;
    }

    public void addProduct(Product product) {
        validate(product);

        if (products.size() >= 20){
            throw new IllegalStateException("Wishlist limit reached: 20 products.");
        }


        if (containsProduct(product.getProductId())) {
            throw new IllegalArgumentException("Product already exists in this wishlist.");
        }

        products.add(product);
    }

    private void validate(Product product) {
        if (product == null) {
            throw new NullPointerException("Product must not be null.");
        }

        if (product.getProductId() == null) {
            throw new IllegalArgumentException("Product ID must not be null.");
        }
    }


    public void removeProduct(String productId) {
        products.removeIf(p -> p.getProductId().equals(productId));
    }

    public boolean containsProduct(String productId) {
        return products.stream().anyMatch(p -> p.getProductId().equals(productId));
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getClientId() {
        return clientId;
    }
}
