package com.br.api.wishlist.adapters.inbound.controller.response;

import java.util.List;

public class WishlistResponse {

    private String clientId;
    private List<String> productIds;

    public WishlistResponse(String clientId, List<String> productIds) {
        this.clientId = clientId;
        this.productIds = productIds;
    }

    public String getClientId() {
        return clientId;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }
}
