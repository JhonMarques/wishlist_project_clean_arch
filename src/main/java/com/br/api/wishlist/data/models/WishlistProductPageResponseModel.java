package com.br.api.wishlist.data.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class WishlistProductPageResponseModel {


    private List<String> productsIds;

    public WishlistProductPageResponseModel(List<String> listProductsIds) {
        this.productsIds = listProductsIds;
    }

    public List<String> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<String> productsIds) {
        this.productsIds = productsIds;
    }


}