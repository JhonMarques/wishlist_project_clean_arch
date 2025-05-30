package com.br.api.wishlist.domain.converters;

import com.br.api.wishlist.domain.entities.Wishlist;
import com.br.api.wishlist.domain.validators.params.WishlistDeleteValidationParams;
import org.springframework.stereotype.Component;

@Component
public class DeleteWishlistValidationParamsConverter {

    public WishlistDeleteValidationParams toValidationParams(Wishlist wishlist, String productId) {
        WishlistDeleteValidationParams params = new WishlistDeleteValidationParams();
        params.setSearchedWishlist(wishlist);
        params.setProductId(productId);
        return params;
    }
}
