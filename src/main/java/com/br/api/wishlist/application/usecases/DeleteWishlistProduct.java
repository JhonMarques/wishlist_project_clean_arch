package com.br.api.wishlist.application.usecases;

import java.util.ArrayList;
import java.util.List;

import com.br.api.wishlist.domain.converters.DeleteWishlistValidationParamsConverter;
import com.br.api.wishlist.domain.entities.WishlistProduct;
import com.br.api.wishlist.domain.entities.Wishlist;
import com.br.api.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.wishlist.domain.validators.delete.WishlistDeleteValidator;
import com.br.api.wishlist.domain.validators.objectid.WishlistObjectIdValidator;
import org.springframework.stereotype.Service;

@Service
public class DeleteWishlistProduct {

    private final IWishlistRepository wishlistRepository;
    private final WishlistDeleteValidator wishlistDeleteValidator;
    private final WishlistObjectIdValidator wishlistObjectIdValidator;
    private final DeleteWishlistValidationParamsConverter deleteWishlistValidationParamsConverter;

    public DeleteWishlistProduct(IWishlistRepository wishlistRepository,
                                 WishlistDeleteValidator wishlistDeleteValidator,
                                 WishlistObjectIdValidator wishlistObjectIdValidator,
                                 DeleteWishlistValidationParamsConverter deleteWishlistValidationParamsConverter) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistDeleteValidator = wishlistDeleteValidator;
        this.wishlistObjectIdValidator = wishlistObjectIdValidator;
        this.deleteWishlistValidationParamsConverter = deleteWishlistValidationParamsConverter;
    }

    public void call(String clientId, String productId) {
        validateIds(clientId, productId);

        Wishlist wishlist = wishlistRepository.findByClientId(clientId);
        validateDeletion(wishlist, productId);

        List<WishlistProduct> updatedProducts = removeProductFromWishlist(wishlist, productId);
        updateOrDeleteWishlist(wishlist, updatedProducts);
    }

    private void validateIds(String clientId, String productId) {
        wishlistObjectIdValidator.validate(List.of(clientId, productId));
    }

    private void validateDeletion(Wishlist wishlist, String productId) {
        var params = deleteWishlistValidationParamsConverter.toValidationParams(wishlist, productId);
        wishlistDeleteValidator.validate(params);
    }

    private List<WishlistProduct> removeProductFromWishlist(Wishlist wishlist, String productId) {
        List<WishlistProduct> products = new ArrayList<>(wishlist.getProducts());

        boolean removed = products.removeIf(product -> product.getProductId().equals(productId));

        if (!removed) {
            throw new IllegalArgumentException("Product not found in wishlist");
        }

        return products;
    }

    private void updateOrDeleteWishlist(Wishlist wishlist, List<WishlistProduct> updatedProducts) {
        if (updatedProducts.isEmpty()) {
            wishlistRepository.deleteWishlistById(wishlist.getId());
        } else {
            wishlist.setProducts(updatedProducts);
            wishlistRepository.saveWishlistProduct(wishlist);
        }
    }

}
