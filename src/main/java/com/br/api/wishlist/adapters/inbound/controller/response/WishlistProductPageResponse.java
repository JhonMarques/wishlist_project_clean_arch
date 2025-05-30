package com.br.api.wishlist.adapters.inbound.controller.response;

import java.util.List;

public class WishlistProductPageResponse {

    private List<String> productIds;

    public WishlistProductPageResponse(List<String> productIds) {
        this.productIds = productIds;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }
}
