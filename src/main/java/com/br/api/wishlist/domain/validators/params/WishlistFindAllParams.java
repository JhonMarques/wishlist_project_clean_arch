package com.br.api.wishlist.domain.validators.params;

import com.br.api.wishlist.domain.entities.Product;
import com.br.api.wishlist.domain.validators.findall.WishlistFindAllValidationParams;

import java.util.List;


public class WishlistFindAllParams extends WishlistFindAllValidationParams {

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
