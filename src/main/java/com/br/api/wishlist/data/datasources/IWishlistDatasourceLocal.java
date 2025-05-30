package com.br.api.wishlist.data.datasources;

import com.br.api.wishlist.data.models.WishlistDocumentModel;
import com.br.api.wishlist.data.models.WishlistPageDocumentModel;
import com.br.api.wishlist.data.models.WishlistProductModel;

import java.util.List;


public interface IWishlistDatasourceLocal {
	
	public void saveWishlistProduct(WishlistDocumentModel wishlistModel);

	public List<WishlistProductModel> getAllProductsWishlistClient(WishlistPageDocumentModel wishlistPageModel);

	public WishlistDocumentModel findByClientId(String clientId);
	
	public boolean existsByClientId(String clientId);

	public void deleteById(String id);

	public boolean productExistInWishlistByClientId(String clientId, String productId);


}
