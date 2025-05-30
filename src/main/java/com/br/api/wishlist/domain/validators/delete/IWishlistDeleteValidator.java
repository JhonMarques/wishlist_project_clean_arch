package com.br.api.wishlist.domain.validators.delete;


import com.br.api.wishlist.domain.validators.params.WishlistDeleteValidationParams;

public interface IWishlistDeleteValidator {

    void validate(WishlistDeleteValidationParams params);
}
