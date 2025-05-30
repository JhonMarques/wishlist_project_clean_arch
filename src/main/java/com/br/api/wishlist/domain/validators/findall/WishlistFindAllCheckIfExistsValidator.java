package com.br.api.wishlist.domain.validators.findall;

import com.br.api.wishlist.domain.exceptions.WishlistDoesNotExistException;
import org.springframework.stereotype.Component;

@Component
public class WishlistFindAllCheckIfExistsValidator implements IWishlistFindAllValidation {

    @Override
    public void validate(WishlistFindAllValidationParams params) {
        if(!params.isExistsWishlist()) {
            throw new WishlistDoesNotExistException("There is no withlist to this client to find all");
        }
    }
}
