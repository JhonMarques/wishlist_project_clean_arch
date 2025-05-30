package com.br.api.wishlist.adapters.inbound.controller.mappers;

import com.br.api.wishlist.adapters.inbound.controller.response.WishlistResponse;
import com.br.api.wishlist.domain.entities.Wishlist;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class WishlistResponseMapper {

    public WishlistResponse toResponse(Wishlist wishlist) {
        if (wishlist == null) return null;

        return new WishlistResponse(
                wishlist.getClientId(),
                wishlist.getProducts()
                        .stream()
                        .map(product -> product.getProductId())
                        .collect(Collectors.toList())
        );
    }
}
