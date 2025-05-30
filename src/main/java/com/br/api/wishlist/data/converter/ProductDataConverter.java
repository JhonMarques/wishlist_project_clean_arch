package com.br.api.wishlist.data.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.br.api.wishlist.data.models.WishlistProductModel;
import com.br.api.wishlist.data.models.WishlistProductPageResponseModel;
import com.br.api.wishlist.domain.entities.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class ProductDataConverter {

    public Product toProduct(WishlistProductModel productModel) {
        if (productModel == null) {
            return null;
        }
        var product = new Product();
        BeanUtils.copyProperties(productModel, product);
        return product;
    }

    public WishlistProductModel toProductModel(Product product) {
        if (product == null) {
            return null;
        }
        var productModel = new WishlistProductModel();
        BeanUtils.copyProperties(product, productModel);
        return productModel;
    }

    public WishlistProductPageResponseModel toProductPageResponse(List<Product> allValues) {
        if(allValues == null || allValues.isEmpty()) {
            return null;
        }

        return new WishlistProductPageResponseModel(allValues.stream().map(Product::getProductId).collect(Collectors.toList()));
    }

}