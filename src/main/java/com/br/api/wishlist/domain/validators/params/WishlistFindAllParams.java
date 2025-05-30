package com.br.api.wishlist.domain.validators.params;

import com.br.api.wishlist.domain.entities.WishlistProduct;
import com.br.api.wishlist.domain.validators.findall.WishlistFindAllValidationParams;

import java.util.List;


public class WishlistFindAllParams extends WishlistFindAllValidationParams {

    private boolean existsWishlist;
    private List<WishlistProduct> listProducts;

    public boolean isExistsWishlist() {
        return existsWishlist;
    }
    public void setExistsWishlist(boolean existsWishlist) {
        this.existsWishlist = existsWishlist;
    }
    public List<WishlistProduct> getListProducts() {
        return listProducts;
    }
    public void setListProducts(List<WishlistProduct> listProducts) {
        this.listProducts = listProducts;
    }


}
