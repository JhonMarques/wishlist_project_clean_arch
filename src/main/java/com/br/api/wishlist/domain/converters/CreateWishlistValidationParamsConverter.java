package com.br.api.wishlist.domain.converters;

import com.br.api.wishlist.domain.entities.Wishlist;
import com.br.api.wishlist.domain.validators.params.WishlistValidationParams;
import org.springframework.stereotype.Component;

@Component
public class CreateWishlistValidationParamsConverter {

    public WishlistValidationParams toValidationParams(Wishlist convertedWishlist, Wishlist searchedWishlist, String productId) {
        return WishlistValidationParams.builder()
                .convertedWishlist(convertedWishlist)
                .searchedWishlist(searchedWishlist)
                .productId(productId)
                .build();
    }
}
