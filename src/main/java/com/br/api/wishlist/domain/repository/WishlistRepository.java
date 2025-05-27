package com.br.api.wishlist.domain.repository;

import com.br.api.wishlist.domain.model.Wishlist;

import java.util.Optional;

public interface WishlistRepository {
    Optional<Wishlist> findByClientId(String clientId);
    void save(Wishlist wishlist);
}
