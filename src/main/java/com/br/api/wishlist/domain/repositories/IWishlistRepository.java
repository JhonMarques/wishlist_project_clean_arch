package com.br.api.wishlist.domain.repositories;

import java.util.List;

import com.br.api.wishlist.domain.entities.Product;
import com.br.api.wishlist.domain.entities.Wishlist;
import com.br.api.wishlist.domain.entities.WishlistPage;

public interface IWishlistRepository {

    void saveWishlistProduct(Wishlist wishlist);

    List<Product> getAllProductsWishlistClient(WishlistPage wishlistPage);

    Wishlist findByClientId(String clientId);

    boolean existsByClientId(String clientId);

    boolean productExistInWishlistByClientId(String clientId, String productId);

    void deleteWishlistById(String id);
}
