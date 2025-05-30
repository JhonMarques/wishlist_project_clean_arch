package com.br.api.wishlist.domain.validators.params;

import com.br.api.wishlist.domain.entities.WishlistProduct;

import java.util.List;

public class WishlistFindAllValidationParams {
    private final boolean wishlistExists;
    private final List<WishlistProduct> products;

    public WishlistFindAllValidationParams(boolean wishlistExists, List<WishlistProduct> products) {
        this.wishlistExists = wishlistExists;
        this.products = products;
    }

    public boolean isWishlistExists() {
        return wishlistExists;
    }

    public List<WishlistProduct> getProducts() {
        return products;
    }
}
