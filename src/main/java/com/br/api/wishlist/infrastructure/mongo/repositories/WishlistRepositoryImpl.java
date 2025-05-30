package com.br.api.wishlist.infrastructure.mongo.repositories;

import com.br.api.wishlist.adapters.outbound.mappers.ProductMapper;
import com.br.api.wishlist.adapters.outbound.mappers.WishlistMapper;
import com.br.api.wishlist.data.datasources.IWishlistDatasourceLocal;
import com.br.api.wishlist.data.models.WishlistProductModel;
import com.br.api.wishlist.domain.entities.WishlistProduct;
import com.br.api.wishlist.domain.entities.Wishlist;
import com.br.api.wishlist.domain.entities.WishlistPage;
import com.br.api.wishlist.domain.repositories.IWishlistRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WishlistRepositoryImpl implements IWishlistRepository {

	private final IWishlistDatasourceLocal wishlistDatasourceLocal;
	private final WishlistMapper wishlistConverter;
	private final ProductMapper productConverter;

	public WishlistRepositoryImpl(IWishlistDatasourceLocal wishlistDatasourceLocal,
								  WishlistMapper wishlistConverter, ProductMapper productConverter) {
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
	public List<WishlistProduct> getAllProductsWishlistClient(WishlistPage wishlistPage) {
		List<WishlistProductModel> models = wishlistDatasourceLocal.getAllProductsWishlistClient(wishlistConverter.toWishlistPageModel(wishlistPage));
        return models.stream().map(productConverter::toDomain).collect(Collectors.toList());
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
