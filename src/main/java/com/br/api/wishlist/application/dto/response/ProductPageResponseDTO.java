package com.br.api.wishlist.application.dto.response;


import java.util.List;

public class ProductPageResponseDTO {

    private List<String> productIds;

    public ProductPageResponseDTO(List<String> productIds) {
        this.productIds = productIds;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }
}
