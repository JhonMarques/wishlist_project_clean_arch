package com.br.api.wishlist.data.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class WishlistRequestDataModel {

    @NotBlank
    @Size(min = 24, max = 24, message = "The size of clientId should be 24")
    @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "clientId is not a valid ObjectId pattern")
    private String clientId;

    @NotBlank
    @Size(min = 24, max = 24, message = "The size of productId should be 24")
    @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "productId is not a valid ObjectId pattern")
    private String productId;

    public WishlistRequestDataModel(String clientId, String productId) {
        this.clientId = clientId;
        this.productId = productId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}