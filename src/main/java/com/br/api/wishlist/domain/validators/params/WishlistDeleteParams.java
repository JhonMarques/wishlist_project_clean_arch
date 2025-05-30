package com.br.api.wishlist.domain.validators.params;

import com.br.api.wishlist.domain.entities.Wishlist;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishlistDeleteParams {
    private Wishlist searchedWishlist;
    private String productId;
}
