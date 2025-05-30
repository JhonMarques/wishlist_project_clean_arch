package com.br.api.wishlist.adapters.inbound.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistRequest {

    @NotBlank(message = "ClientId cannot be blank")
    private String clientId;

    @NotBlank(message = "ProductId cannot be blank")
    private String productId;
}
