package com.br.api.wishlist.data.converter;


import java.util.Arrays;
import java.util.stream.Collectors;

import com.br.api.wishlist.data.models.*;
import org.bson.types.ObjectId;
import com.br.api.wishlist.domain.entities.Wishlist;
import com.br.api.wishlist.domain.entities.WishlistPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class WishlistDataConverter {

    private final ProductDataConverter productConverter;

    public WishlistDataConverter(ProductDataConverter productConverter) {
        this.productConverter = productConverter;
    }

    public Wishlist toWishlist(WishlistDocumentModel wishlistModel) {
        if (wishlistModel == null) {
            return null;
        }
        var wishlist = new Wishlist();
        wishlist.setId(wishlistModel.getId());
        wishlist.setClientId(wishlistModel.getClientId());
        wishlist.setProducts(
                wishlistModel.getProducts().stream().map(productConverter::toProduct).collect(Collectors.toList()));
        return wishlist;
    }

    public WishlistDocumentModel toWishlistModel(Wishlist wishlist) {
        if (wishlist == null) {
            return null;
        }
        var wishlistModel = new WishlistDocumentModel();
        wishlistModel.setId(wishlist.getId() != null ? wishlist.getId() : null);
        wishlistModel.setClientId(wishlist.getClientId());
        wishlistModel.setProducts(
                wishlist.getProducts().stream().map(productConverter::toProductModel).collect(Collectors.toList()));
        return wishlistModel;
    }

    public Wishlist toWishlist(WishlistProductPageRequestModel wishlistRequestModel) {
        if (wishlistRequestModel == null) {
            return null;
        }
        var wishlist = new Wishlist();
        BeanUtils.copyProperties(wishlistRequestModel, wishlist);
        var product = new WishlistProductModel();
        wishlist.setProducts(Arrays.asList(productConverter.toProduct(product)));
        return wishlist;
    }

    public WishlistPage toWishlistPage(WishlistProductPageRequestModel wishlistPageRequestModel) {
        if (wishlistPageRequestModel == null) {
            return null;
        }
        var wishlistPage = new WishlistPage();
        BeanUtils.copyProperties(wishlistPageRequestModel, wishlistPage);
        return wishlistPage;
    }

    public WishlistPageDocumentModel toWishlistPageModel(WishlistPage wishlistPage) {
        if (wishlistPage == null) {
            return null;
        }
        var wishlistPageDocumentModel = new WishlistPageDocumentModel();
        BeanUtils.copyProperties(wishlistPage, wishlistPageDocumentModel);
        return wishlistPageDocumentModel;
    }

    public Wishlist toWishlist(WishlistRequestDataModel wishlistRequestModel) {
        if (wishlistRequestModel == null) {
            return null;
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setClientId(wishlistRequestModel.getClientId());

        if (wishlistRequestModel.getProductId() == null || wishlistRequestModel.getProductId().isBlank()) {
            throw new IllegalArgumentException("Product ID cannot be null or blank");
        }

        WishlistProductModel productModel = new WishlistProductModel();
        productModel.setProductId(wishlistRequestModel.getProductId());

        wishlist.setProducts(Arrays.asList(productConverter.toProduct(productModel)));

        return wishlist;
    }

}
