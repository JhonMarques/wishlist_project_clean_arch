package com.br.api.wishlist.adapters.inbound.controller.mappers;

import com.br.api.wishlist.adapters.inbound.controller.response.WishlistProductPageResponse;
import com.br.api.wishlist.domain.entities.WishlistProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WishlistProductResponseMapper {

    public WishlistProductPageResponse toProductPageResponse(List<WishlistProduct> allValues) {
        if (allValues == null || allValues.isEmpty()) {
            return null;
        }

        return new WishlistProductPageResponse(
                allValues.stream()
                        .map(WishlistProduct::getProductId)
                        .collect(Collectors.toList())
        );
    }
}
