package com.br.api.wishlist.domain.validators.delete;

import com.br.api.wishlist.domain.exceptions.WishlistDoesNotExistException;
import com.br.api.wishlist.domain.validators.params.WishlistDeleteValidationParams;
import org.springframework.stereotype.Component;

@Component
public class WishlistCheckIfItExistsValidator implements IWishlistDeleteValidator {

    @Override
    public void validate(WishlistDeleteValidationParams params) {
        if (params.getSearchedWishlist() == null) {
            throw new WishlistDoesNotExistException("There is no wishlist for this client to delete.");
        }
    }
}