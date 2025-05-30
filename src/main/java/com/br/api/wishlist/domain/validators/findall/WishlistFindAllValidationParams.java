package com.br.api.wishlist.domain.validators.findall;

import com.br.api.wishlist.domain.entities.Product;

import java.util.List;

public class WishlistFindAllValidationParams {

    private boolean existsWishlist;
    private List<Product> listProducts;

    public boolean isExistsWishlist() {
        return existsWishlist;
    }
    public void setExistsWishlist(boolean existsWishlist) {
        this.existsWishlist = existsWishlist;
    }
    public List<Product> getListProducts() {
        return listProducts;
    }
    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

}