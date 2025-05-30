package com.br.api.wishlist.application.usecases;

import java.util.Arrays;

import com.br.api.wishlist.domain.exceptions.WishlistDoesNotExistException;
import com.br.api.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.wishlist.domain.validators.objectid.WishlistObjectIdValidator;
import org.springframework.stereotype.Service;

@Service
public class GetProductWishlistClient {

    private final IWishlistRepository wishlistRepository;
    private final WishlistObjectIdValidator wishlistObjectIdValidator;

    public GetProductWishlistClient(IWishlistRepository wishlistRepository,
                                    WishlistObjectIdValidator wishlistObjectIdValidator) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistObjectIdValidator = wishlistObjectIdValidator;
    }

    public boolean call(String clientId, String productId) {

        this.wishlistObjectIdValidator.validate(Arrays.asList(clientId, productId));

        var existsWishlist = wishlistRepository.existsByClientId(clientId);

        if (!existsWishlist) {
            throw new WishlistDoesNotExistException("This wishlist was not found");
        }

        return wishlistRepository.productExistInWishlistByClientId(clientId, productId);
    }

}
