package com.br.api.wishlist.domain.converters;

import com.br.api.wishlist.domain.entities.WishlistProduct;
import com.br.api.wishlist.domain.validators.params.WishlistFindAllParams;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllWishlistValidationParamsConverter {

    public WishlistFindAllParams toFindAllValidationParams(boolean existsWishlist, List<WishlistProduct> products) {
        WishlistFindAllParams params = new WishlistFindAllParams();
        params.setExistsWishlist(existsWishlist);
        params.setListProducts(products);
        return params;
    }
}
