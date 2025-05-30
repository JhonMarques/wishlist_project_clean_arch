package com.br.api.wishlist.domain.validators.create;

import com.br.api.wishlist.domain.exceptions.WishlistCapacityExceededException;
import com.br.api.wishlist.domain.validators.params.WishlistCreateValidatorParams;
import com.br.api.wishlist.domain.validators.params.WishlistValidationParams;
import org.springframework.stereotype.Component;

@Component
public class WishlistProductLimitSizeValidator implements IWishlistCreateValidator {

    private static final int MAX_CAPACITY = 20;

    @Override
    public void validate(WishlistCreateValidatorParams params) {
        if (params.getSearchedWishlist().getProducts().size() >= MAX_CAPACITY) {
            throw new WishlistCapacityExceededException("Wishlist already reached the limit of products: " + MAX_CAPACITY);
        }
    }

    @Override
    public void validate(WishlistValidationParams params) {

    }
}
