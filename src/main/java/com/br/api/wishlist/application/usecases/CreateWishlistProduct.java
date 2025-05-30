package com.br.api.wishlist.application.usecases;

import java.util.Optional;

import com.br.api.wishlist.domain.converters.CreateWishlistValidationParamsConverter;
import com.br.api.wishlist.domain.entities.WishlistProduct;
import com.br.api.wishlist.domain.entities.Wishlist;
import com.br.api.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.wishlist.domain.validators.create.WishlistCreateValidator;
import org.springframework.stereotype.Service;

@Service
public class CreateWishlistProduct {

    private final IWishlistRepository wishlistRepository;
    private final WishlistCreateValidator wishlistValidator;
    private final CreateWishlistValidationParamsConverter createWishlistValidationParamsConverter;

    public CreateWishlistProduct(IWishlistRepository wishlistRepository,
                                 WishlistCreateValidator wishlistValidator,
                                 CreateWishlistValidationParamsConverter createWishlistValidationParamsConverter) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistValidator = wishlistValidator;
        this.createWishlistValidationParamsConverter = createWishlistValidationParamsConverter;
    }

    public boolean call(Wishlist wishlist) {
        Optional<WishlistProduct> optionalProduct = wishlist.getProducts()
                .stream()
                .findFirst();

        if (optionalProduct.isEmpty()) {
            throw new IllegalArgumentException("No product provided in wishlist");
        }

        WishlistProduct newProduct = optionalProduct.get();

        boolean existsWishlist = wishlistRepository.existsByClientId(wishlist.getClientId());

        if (!existsWishlist) {
            wishlistRepository.saveWishlistProduct(wishlist);
            return true;
        }

        var createdWishlist = wishlistRepository.findByClientId(wishlist.getClientId());
        this.wishlistValidator.validate(
                createWishlistValidationParamsConverter.toValidationParams(wishlist, createdWishlist,
                        String.valueOf(newProduct.getProductId()))
        );
        createdWishlist.getProducts().add(newProduct);
        wishlistRepository.saveWishlistProduct(createdWishlist);
        return false;
    }
}