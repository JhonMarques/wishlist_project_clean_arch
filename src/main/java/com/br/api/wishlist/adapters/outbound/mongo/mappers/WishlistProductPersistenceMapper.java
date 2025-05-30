package com.br.api.wishlist.adapters.outbound.mongo.mappers;

import com.br.api.wishlist.data.models.WishlistProductModel;
import com.br.api.wishlist.domain.entities.WishlistProduct;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class WishlistProductPersistenceMapper {

    public WishlistProduct toDomain(WishlistProductModel productModel) {
        if (productModel == null) {
            return null;
        }
        WishlistProduct product = new WishlistProduct();
        BeanUtils.copyProperties(productModel, product);
        return product;
    }

    public WishlistProductModel toModel(WishlistProduct product) {
        if (product == null) {
            return null;
        }
        WishlistProductModel productModel = new WishlistProductModel();
        BeanUtils.copyProperties(product, productModel);
        return productModel;
    }
}
