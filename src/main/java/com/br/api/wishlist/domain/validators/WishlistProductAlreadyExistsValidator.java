package com.br.api.wishlist.domain.validators;

import com.br.api.wishlist.domain.exceptions.ProductAlreadyInWishlistException;
import com.br.api.wishlist.domain.validators.create.IWishlistCreateValidator;
import com.br.api.wishlist.domain.validators.params.WishlistCreateValidatorParams;
import com.br.api.wishlist.domain.validators.params.WishlistValidationParams;
import org.springframework.stereotype.Component;


@Component
public class WishlistProductAlreadyExistsValidator implements IWishlistCreateValidator {

    @Override
    public void validate(WishlistValidationParams params) {
        boolean alreadyExists = params.getSearchedWishlist()
                .getProducts()
                .stream()
                .anyMatch(product -> product.getProductId().equals(params.getProductId()));

        if (alreadyExists) {
            throw new ProductAlreadyInWishlistException("Product already exists in this wishlist");
        }
    }
}