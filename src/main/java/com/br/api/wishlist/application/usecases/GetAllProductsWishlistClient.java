package com.br.api.wishlist.application.usecases;


import java.util.Arrays;
import java.util.List;

import com.br.api.wishlist.domain.converters.FindAllWishlistValidationParamsConverter;
import com.br.api.wishlist.domain.entities.WishlistProduct;
import com.br.api.wishlist.domain.entities.WishlistPage;
import com.br.api.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.wishlist.domain.validators.findall.WishlistFindAllValidator;
import com.br.api.wishlist.domain.validators.objectid.WishlistObjectIdValidator;
import org.springframework.stereotype.Service;


@Service
public class GetAllProductsWishlistClient {

    private final IWishlistRepository wishlistRepository;
    private final WishlistObjectIdValidator wishlistObjectIdValidator;
    private final WishlistFindAllValidator wishlistFindAllValidator;
    private final FindAllWishlistValidationParamsConverter validationParamsFindAllConverter;

    public GetAllProductsWishlistClient(IWishlistRepository wishlistRepository,
                                        WishlistObjectIdValidator wishlistObjectIdValidator, WishlistFindAllValidator wishlistFindAllValidator,
                                        FindAllWishlistValidationParamsConverter validationParamsFindAllConverter) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistObjectIdValidator = wishlistObjectIdValidator;
        this.wishlistFindAllValidator = wishlistFindAllValidator;
        this.validationParamsFindAllConverter = validationParamsFindAllConverter;
    }

    public List<WishlistProduct> call(WishlistPage wishlistPage) {
        if (wishlistPage == null || wishlistPage.getClientId() == null || wishlistPage.getPage() == null) {
            throw new IllegalArgumentException("Client ID and page are required.");
        }

        this.wishlistObjectIdValidator.validate(Arrays.asList(wishlistPage.getClientId()));

        boolean existsWishlist = wishlistRepository.existsByClientId(wishlistPage.getClientId());
        if (!existsWishlist) {
            throw new IllegalArgumentException("Wishlist not found");
        }

        wishlistPage.setPage(wishlistPage.getPage() * 5);

        var allProductsIds = wishlistRepository.getAllProductsWishlistClient(wishlistPage);

        wishlistFindAllValidator
                .validate(validationParamsFindAllConverter.toFindAllValidationParams(existsWishlist, allProductsIds));

        return allProductsIds;
    }


}