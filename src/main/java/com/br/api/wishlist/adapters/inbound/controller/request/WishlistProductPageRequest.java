package com.br.api.wishlist.adapters.inbound.controller.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class WishlistProductPageRequest {

    @NotBlank
    @Size(min = 24, max = 24, message = "The size of clientId should be 24")
    @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "clientId is not a valid ObjectId pattern")
    private String clientId;

    @NotNull(message = "A page value is required starting from 0")
    @Min(value = 0, message = "Page number must be a non-negative integer")
    private Integer page;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}