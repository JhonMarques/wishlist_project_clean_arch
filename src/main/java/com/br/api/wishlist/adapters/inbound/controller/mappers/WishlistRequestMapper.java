package com.br.api.wishlist.adapters.inbound.controller.mappers;

import com.br.api.wishlist.adapters.inbound.controller.request.WishlistProductPageRequest;
import com.br.api.wishlist.adapters.inbound.controller.request.WishlistRequest;
import com.br.api.wishlist.domain.entities.Wishlist;
import com.br.api.wishlist.domain.entities.WishlistPage;
import com.br.api.wishlist.domain.entities.WishlistProduct;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class WishlistRequestMapper {

    public Wishlist toDomain(WishlistRequest request) {
        if (request == null) return null;

        Wishlist wishlist = new Wishlist();
        wishlist.setClientId(request.getClientId());

        WishlistProduct product = new WishlistProduct();
        product.setProductId(request.getProductId());

        wishlist.setProducts(Collections.singletonList(product));
        return wishlist;
    }

    public WishlistPage toDomain(WishlistProductPageRequest request) {
        if (request == null) return null;

        WishlistPage wishlistPage = new WishlistPage();
        wishlistPage.setClientId(request.getClientId());
        wishlistPage.setPage(request.getPage());
        return wishlistPage;
    }
}
