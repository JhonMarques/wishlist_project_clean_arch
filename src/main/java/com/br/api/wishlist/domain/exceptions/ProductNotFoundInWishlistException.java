package com.br.api.wishlist.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundInWishlistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundInWishlistException(String message) {
        super(message);
    }
}
