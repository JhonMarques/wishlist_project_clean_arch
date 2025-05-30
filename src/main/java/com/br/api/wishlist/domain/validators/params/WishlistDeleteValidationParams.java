package com.br.api.wishlist.domain.validators.params;

import com.br.api.wishlist.domain.entities.Wishlist;

public class WishlistDeleteValidationParams {

    private Wishlist searchedWishlist;
    private String productId;

    public Wishlist getSearchedWishlist() {
        return searchedWishlist;
    }

    public void setSearchedWishlist(Wishlist searchedWishlist) {
        this.searchedWishlist = searchedWishlist;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
