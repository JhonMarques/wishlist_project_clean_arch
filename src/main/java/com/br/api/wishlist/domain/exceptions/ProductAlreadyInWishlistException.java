package com.br.api.wishlist.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductAlreadyInWishlistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProductAlreadyInWishlistException(String message) {
        super(message);
    }
}
