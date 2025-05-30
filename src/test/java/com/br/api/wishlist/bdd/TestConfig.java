package com.br.api.wishlist.bdd;

import com.br.api.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.wishlist.domain.validators.create.WishlistCreateValidator;
import com.br.api.wishlist.domain.validators.objectid.WishlistObjectIdValidator;
import com.br.api.wishlist.domain.validators.delete.WishlistDeleteValidator;
import com.br.api.wishlist.domain.converters.*;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public IWishlistRepository wishlistRepository() {
        return Mockito.mock(IWishlistRepository.class);
    }

    @Bean
    public WishlistCreateValidator wishlistCreateValidator() {
        return Mockito.mock(WishlistCreateValidator.class);
    }

    @Bean
    public WishlistObjectIdValidator wishlistObjectIdValidator() {
        return Mockito.mock(WishlistObjectIdValidator.class);
    }

    @Bean
    public WishlistDeleteValidator wishlistDeleteValidator() {
        return Mockito.mock(WishlistDeleteValidator.class);
    }

    @Bean
    public CreateWishlistValidationParamsConverter createWishlistValidationParamsConverter() {
        return Mockito.mock(CreateWishlistValidationParamsConverter.class);
    }

    @Bean
    public DeleteWishlistValidationParamsConverter deleteWishlistValidationParamsConverter() {
        return Mockito.mock(DeleteWishlistValidationParamsConverter.class);
    }

    @Bean
    public FindAllWishlistValidationParamsConverter findAllWishlistValidationParamsConverter() {
        return Mockito.mock(FindAllWishlistValidationParamsConverter.class);
    }
}
