package com.br.api.wishlist.domain.validators.findall;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WishlistFindAllValidator implements IWishlistFindAllValidation {

    private final List<IWishlistFindAllValidation> validations;

    public WishlistFindAllValidator(List<IWishlistFindAllValidation> validations) {
        this.validations = validations;
    }

    @Override
    public void validate(WishlistFindAllValidationParams params) {
        for (IWishlistFindAllValidation validation : validations) {
            validation.validate(params);
        }
    }
}
