package com.br.api.wishlist.adapters.outbound.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.br.api.wishlist.data.models.WishlistProductModel;
import com.br.api.wishlist.data.models.WishlistProductPageResponseModel;
import com.br.api.wishlist.domain.entities.WishlistProduct;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {

    public WishlistProduct toDomain(WishlistProductModel productModel) {
        if (productModel == null) {
            return null;
        }
        var product = new WishlistProduct();
        BeanUtils.copyProperties(productModel, product);
        return product;
    }

    public WishlistProductModel toModel(WishlistProduct product) {
        if (product == null) {
            return null;
        }
        var productModel = new WishlistProductModel();
        BeanUtils.copyProperties(product, productModel);
        return productModel;
    }



}