package com.br.api.wishlist.domain.entities;

import org.bson.types.ObjectId;

public class Product {

    private ObjectId productId;

    public Product(ObjectId productId) {
        this.productId = productId;
    }

    public Product() {}

    public String getProductId() {
        return productId.toHexString();
    }

    public void setProductId(String productId) {
        this.productId = new ObjectId(productId);
    }

}