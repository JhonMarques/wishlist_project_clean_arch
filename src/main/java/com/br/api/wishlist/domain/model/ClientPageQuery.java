package com.br.api.wishlist.domain.model;

public class ClientPageQuery {
    private final String clientId;
    private final int page;

    public ClientPageQuery(String clientId, int page) {
        this.clientId = clientId;
        this.page = page;
    }

    public String getClientId() {
        return clientId;
    }

    public int getPage() {
        return page;
    }
}

