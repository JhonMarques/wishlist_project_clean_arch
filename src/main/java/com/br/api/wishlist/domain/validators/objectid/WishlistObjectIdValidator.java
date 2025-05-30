package com.br.api.wishlist.domain.validators.objectid;

import java.util.List;

import com.br.api.wishlist.domain.exceptions.InvalidObjectIdFormatException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;


@Component
public class WishlistObjectIdValidator implements IWishlistObjectIdValidator {

    @Override
    public void validate(List<String> objectIds) {
        objectIds.stream()
                .filter(id -> !ObjectId.isValid(id))
                .findFirst()
                .ifPresent(invalidId -> {
                    throw new InvalidObjectIdFormatException(
                            "Invalid ObjectId format: \"" + invalidId + "\". Must be exactly 24 hex characters.");
                });
    }
}
