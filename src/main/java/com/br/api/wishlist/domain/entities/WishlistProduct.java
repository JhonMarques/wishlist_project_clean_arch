package com.br.api.wishlist.domain.entities;

import org.bson.types.ObjectId;

public class WishlistProduct {

    private ObjectId productId;

    public WishlistProduct(ObjectId productId) {
        this.productId = productId;
    }

    public WishlistProduct() {}

    public String getProductId() {
        return productId.toHexString();
    }

    public void setProductId(String productId) {
        this.productId = new ObjectId(productId);
    }

}
