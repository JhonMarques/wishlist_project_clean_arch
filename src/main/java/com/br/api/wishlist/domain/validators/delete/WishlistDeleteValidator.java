package com.br.api.wishlist.domain.validators.delete;

import com.br.api.wishlist.domain.validators.params.WishlistDeleteValidationParams;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WishlistDeleteValidator implements IWishlistDeleteValidator {

    private final List<IWishlistDeleteValidator> validations;

    public WishlistDeleteValidator(List<IWishlistDeleteValidator> validations) {
        this.validations = validations;
    }

    @Override
    public void validate(WishlistDeleteValidationParams params) {
        validations.forEach(validation -> validation.validate(params));
    }
}
