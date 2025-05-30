package com.br.api.wishlist.domain.validators.create;

import java.util.List;

import com.br.api.wishlist.domain.validators.create.IWishlistCreateValidator;
import com.br.api.wishlist.domain.validators.params.WishlistCreateValidatorParams;
import com.br.api.wishlist.domain.validators.params.WishlistValidationParams;
import org.springframework.stereotype.Component;

@Component
public class WishlistCreateValidator implements IWishlistCreateValidator {

    private final List<IWishlistCreateValidator> validations;

    public WishlistCreateValidator(List<IWishlistCreateValidator> validations) {
        this.validations = validations;
    }

    @Override
    public void validate(WishlistCreateValidatorParams params) {

    }

    @Override
    public void validate(WishlistValidationParams params) {
        for (IWishlistCreateValidator validation : validations) {
            validation.validate(params);
        }
    }

}