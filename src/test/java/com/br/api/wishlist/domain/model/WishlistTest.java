package com.br.api.wishlist.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WishlistTest {

    @Test
    void shouldAddProductSuccessfully() {
        Wishlist wishlist = new Wishlist("tmz480kC80Rgoit81l3ktrwb");
        Product product = new Product("507f1f77bcf86cd799439011");

        wishlist.addProduct(product);

        assertTrue(wishlist.getProducts().contains(product));
    }

    @Test
    void shouldThrowWhenExceedingProductLimit() {
        Wishlist wishlist = new Wishlist("tmz480kC80Rgoit81l3ktrwb");
        for (int i = 0; i < 20; i++) {
            wishlist.addProduct(new Product(String.format("%024d", i)));
        }

        assertThrows(IllegalStateException.class, () ->
                wishlist.addProduct(new Product("aaaaaaaaaaaaaaaaaaaaaaaa")));
    }
}
