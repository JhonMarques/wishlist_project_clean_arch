package com.br.api.wishlist.domain.validators.params;

import com.br.api.wishlist.domain.entities.Wishlist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDeleteValidationParams {
    private Wishlist searchedWishlist;
    private String productId;
}
