package com.br.api.wishlist.domain.validators.params;

import com.br.api.wishlist.domain.entities.Wishlist;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WishlistCreateValidatorParams {

    private final Wishlist convertedWishlist;
    private final Wishlist searchedWishlist;
    private final String productId;

}