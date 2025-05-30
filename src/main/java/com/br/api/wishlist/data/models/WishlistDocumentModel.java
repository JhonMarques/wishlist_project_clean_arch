package com.br.api.wishlist.data.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "wishlists")
public class WishlistDocumentModel {

    @Id
    private ObjectId id;

    @Field("clientId")
    private ObjectId clientId;

    private List<WishlistProductModel> products;

    public String getId() {
        return id.toHexString();
    }

    public void setId(String id) {
        this.id = null;
        if (id != null) {
            this.id = new ObjectId(id);
        }
    }

    public String getClientId() {
        return clientId.toHexString();
    }

    public void setClientId(String clientId) {
        this.clientId = new ObjectId(clientId);
    }

    public List<WishlistProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<WishlistProductModel> products) {
        this.products = products;
    }
}