package com.br.api.wishlist.domain.validators.delete;


import com.br.api.wishlist.domain.exceptions.WishlistDoesNotExistException;
import com.br.api.wishlist.domain.validators.params.WishlistDeleteValidationParams;
import org.springframework.stereotype.Component;

@Component
public class WishlistCheckIfProductExistsInValidator implements IWishlistDeleteValidator {

    @Override
    public void validate(WishlistDeleteValidationParams params) {
        boolean exists = params.getSearchedWishlist().getProducts().stream()
                .anyMatch(p -> p.getProductId().equals(params.getProductId()));

        if (!exists) {
            throw new WishlistDoesNotExistException("This product does not exist in this wishlist to remove it.");
        }
    }

}