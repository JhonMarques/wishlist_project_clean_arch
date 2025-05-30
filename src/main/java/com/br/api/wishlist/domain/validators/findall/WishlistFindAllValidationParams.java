package com.br.api.wishlist.domain.validators.findall;

import com.br.api.wishlist.domain.entities.WishlistProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistFindAllValidationParams {

    private boolean existsWishlist;
    private List<WishlistProduct> listProducts;

}
