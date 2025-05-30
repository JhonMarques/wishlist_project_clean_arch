package com.br.api.wishlist.data.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class WishlistProductModel {


    @Field("productId")
    private ObjectId productId;

    public WishlistProductModel(ObjectId productId) {
        this.productId = productId;
    }

    public WishlistProductModel() {
    }

    public String getProductId() {
        return productId.toHexString();
    }

    public void setProductId(String productId) {
        this.productId = new ObjectId(productId);
    }



}