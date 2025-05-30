package com.br.api.wishlist.domain.validators.findall;

import com.br.api.wishlist.domain.exceptions.NoMoreProductsInWishlistException;
import org.springframework.stereotype.Component;

@Component
public class WishlistFindAllNoMorePagesLeftValidator implements IWishlistFindAllValidation {

    @Override
    public void validate(WishlistFindAllValidationParams params) {
        if (params.getListProducts().isEmpty()) {
            throw new NoMoreProductsInWishlistException("There is no more pages left with products");
        }
    }
}

