package com.br.api.wishlist.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidObjectIdFormatException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidObjectIdFormatException(String message) {
        super(message);
    }
}
