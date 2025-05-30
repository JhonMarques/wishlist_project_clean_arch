package com.br.api.wishlist.application.usecases;

import com.br.api.wishlist.adapters.outbound.mappers.WishlistMapper;
import com.br.api.wishlist.domain.entities.Wishlist;
import com.br.api.wishlist.domain.exceptions.WishlistDoesNotExistException;
import com.br.api.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.wishlist.domain.validators.objectid.WishlistObjectIdValidator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetWishlistByClientId {

    private final IWishlistRepository wishlistRepository;
    private final WishlistObjectIdValidator wishlistObjectIdValidator;
    private final WishlistMapper wishlistMapper;

    public GetWishlistByClientId(
            IWishlistRepository wishlistRepository,
            WishlistObjectIdValidator wishlistObjectIdValidator,
            WishlistMapper wishlistMapper
    ) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistObjectIdValidator = wishlistObjectIdValidator;
        this.wishlistMapper = wishlistMapper;
    }

    public Wishlist call(String clientId) {
        wishlistObjectIdValidator.validate(List.of(clientId));
        ObjectId objectId = new ObjectId(clientId);

        return wishlistRepository.findByClientId(objectId)
                .map(wishlistMapper::toDomain)
                .orElseThrow(() -> new WishlistDoesNotExistException("Wishlist not found for clientId: " + clientId));
    }
}
