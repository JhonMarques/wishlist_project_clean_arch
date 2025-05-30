package com.br.api.wishlist.domain.validators.create;


import com.br.api.wishlist.domain.validators.params.WishlistCreateValidatorParams;
import com.br.api.wishlist.domain.validators.params.WishlistValidationParams;

public interface IWishlistCreateValidator {

    void validate(WishlistCreateValidatorParams params);

    void validate(WishlistValidationParams params);

}