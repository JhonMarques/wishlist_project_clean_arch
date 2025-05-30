package com.br.api.wishlist.domain.entities;

import org.bson.types.ObjectId;

import java.util.List;

public class Wishlist {

    private ObjectId id;
    private ObjectId clientId;

    private List<WishlistProduct> products;

    public Wishlist() {

    }

    public Wishlist(String clientId) {
        this.clientId = new ObjectId(clientId);
    }

    public String getId() {
        if (id != null) {
            return id.toHexString();
        }
        return null;
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    public String getClientId() {
        return clientId.toHexString();
    }

    public void setClientId(String clientId) {
        this.clientId = new ObjectId(clientId);
    }

    public List<WishlistProduct> getProducts() {
        return products;
    }

    public void setProducts(List<WishlistProduct> products) {
        this.products = products;
    }
}