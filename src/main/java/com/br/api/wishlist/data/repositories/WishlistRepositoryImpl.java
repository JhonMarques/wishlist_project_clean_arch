package com.br.api.wishlist.data.repositories;

import com.br.api.wishlist.data.converter.ProductDataConverter;
import com.br.api.wishlist.data.converter.WishlistDataConverter;
import com.br.api.wishlist.data.datasources.IWishlistDatasourceLocal;
import com.br.api.wishlist.data.models.WishlistProductModel;
import com.br.api.wishlist.domain.entities.Product;
import com.br.api.wishlist.domain.entities.Wishlist;
import com.br.api.wishlist.domain.entities.WishlistPage;
import com.br.api.wishlist.domain.repositories.IWishlistRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WishlistRepositoryImpl implements IWishlistRepository {

	private final IWishlistDatasourceLocal wishlistDatasourceLocal;
	private final WishlistDataConverter wishlistConverter;
	private final ProductDataConverter productConverter;

	public WishlistRepositoryImpl(IWishlistDatasourceLocal wishlistDatasourceLocal,
                                  WishlistDataConverter wishlistConverter, ProductDataConverter productConverter) {
		this.wishlistDatasourceLocal = wishlistDatasourceLocal;
		this.wishlistConverter = wishlistConverter;
		this.productConverter = productConverter;
	}

	@Override
	public void saveWishlistProduct(Wishlist wishlist) {
		var wishlistModel = wishlistConverter.toWishlistModel(wishlist);
		wishlistDatasourceLocal.saveWishlistProduct(wishlistModel);

	}

	@Override
	public List<Product> getAllProductsWishlistClient(WishlistPage wishlistPage) {
		List<WishlistProductModel> models = wishlistDatasourceLocal.getAllProductsWishlistClient(wishlistConverter.toWishlistPageModel(wishlistPage));
        return models.stream().map(productConverter::toProduct).collect(Collectors.toList());
	}

	@Override
	public Wishlist findByClientId(String clientId) {
		 var wishlistModel = wishlistDatasourceLocal.findByClientId(clientId);
		 return wishlistConverter.toWishlist(wishlistModel);
	}

	@Override
	public boolean existsByClientId(String clientId) {
		return wishlistDatasourceLocal.existsByClientId(clientId);
	}

	@Override
	public void deleteWishlistById(String id) {
		wishlistDatasourceLocal.deleteById(id);		
	}

	@Override
	public boolean productExistInWishlistByClientId(String clientId, String productId) {
		return wishlistDatasourceLocal.productExistInWishlistByClientId(clientId, productId);
	}

}
