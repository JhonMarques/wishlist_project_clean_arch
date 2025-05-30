package com.br.api.wishlist.bdd;

import com.br.api.wishlist.domain.converters.CreateWishlistValidationParamsConverter;
import com.br.api.wishlist.domain.entities.WishlistProduct;
import com.br.api.wishlist.domain.entities.Wishlist;
import com.br.api.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.wishlist.application.usecases.CreateWishlistProduct;
import com.br.api.wishlist.domain.validators.create.WishlistCreateValidator;
import com.br.api.wishlist.domain.validators.params.WishlistValidationParams;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateWishlistProductSteps {

    private IWishlistRepository wishlistRepository;
    private WishlistCreateValidator wishlistValidator;
    private CreateWishlistValidationParamsConverter validationParamsConverter;
    private CreateWishlistProduct createWishlistProduct;

    private Wishlist wishlist;
    private boolean isNew;
    private Exception thrownException;

    @Before
    public void setup() {
        wishlistRepository = mock(IWishlistRepository.class);
        wishlistValidator = mock(WishlistCreateValidator.class);
        validationParamsConverter = mock(CreateWishlistValidationParamsConverter.class);
        createWishlistProduct = new CreateWishlistProduct(wishlistRepository, wishlistValidator, validationParamsConverter);
    }

    @Given("the client {string} does not have a wishlist")
    public void the_client_does_not_have_a_wishlist(String clientId) {
        clientId = new ObjectId().toHexString();
        when(wishlistRepository.existsByClientId(clientId)).thenReturn(false);
        wishlist = new Wishlist();
        wishlist.setClientId(clientId);
    }

    @Given("the client sends a wishlist with productId {string}")
    public void the_client_sends_a_wishlist_with_productId(String productId) {
        WishlistProduct product = new WishlistProduct(new ObjectId(productId));
        List<WishlistProduct> products = new ArrayList<>();
        products.add(product);
        wishlist.setProducts(products);
    }

    @When("the wishlist is created")
    public void the_wishlist_is_created() {
        try {
            WishlistValidationParams mockParams = mock(WishlistValidationParams.class);

            when(validationParamsConverter.toValidationParams(any(), any(), any()))
                    .thenReturn(mockParams);

            isNew = createWishlistProduct.call(wishlist);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("the wishlist should be saved")
    public void the_wishlist_should_be_saved() {
        verify(wishlistRepository, times(1)).saveWishlistProduct(any(Wishlist.class));
    }

    @And("the response should indicate the wishlist was created")
    public void the_response_should_indicate_creation() {
        assertTrue(isNew);
    }

    @Given("the client {string} already has a wishlist")
    public void the_client_already_has_a_wishlist(String clientId) {
        when(wishlistRepository.existsByClientId(clientId)).thenReturn(true);
        Wishlist existingWishlist = new Wishlist();
        existingWishlist.setClientId(clientId);
        existingWishlist.setProducts(new ArrayList<>());
        when(wishlistRepository.findByClientId(clientId)).thenReturn(existingWishlist);

        wishlist = new Wishlist();
        wishlist.setClientId(clientId);
    }

    @Given("the client sends a wishlist with a new productId {string}")
    public void the_client_sends_a_wishlist_with_new_productId(String productId) {
        WishlistProduct newProduct = new WishlistProduct(new ObjectId(productId));
        wishlist.setProducts(List.of(newProduct));
    }

    @Then("the product should be validated and added to the existing wishlist")
    public void the_product_should_be_validated_and_added() {
        verify(wishlistValidator, times(1)).validate((WishlistValidationParams) any());
        verify(wishlistRepository, times(1)).saveWishlistProduct(any(Wishlist.class));
    }

    @And("the response should indicate the wishlist was updated")
    public void the_response_should_indicate_update() {
        assertFalse(isNew);
    }

    @Given("the client {string} sends an empty wishlist")
    public void the_client_sends_an_empty_wishlist(String clientId) {
        wishlist = new Wishlist();
        wishlist.setClientId(clientId);
        wishlist.setProducts(new ArrayList<>());
    }

    @Then("an exception should be thrown stating that no product was provided")
    public void an_exception_should_be_thrown_for_empty_product() {
        assertNotNull(thrownException);
        assertTrue(thrownException instanceof IllegalArgumentException);
        assertEquals("No product provided in wishlist", thrownException.getMessage());
    }

    @Then("the response should indicate that the wishlist was newly created")
    public void the_response_should_indicate_that_the_wishlist_was_newly_created() {
        assertTrue(isNew);
    }

    @Then("an exception should be thrown indicating no product was provided")
    public void an_exception_should_be_thrown_indicating_no_product_was_provided() {
        assertNotNull(thrownException);
        assertTrue(thrownException instanceof IllegalArgumentException);
        assertEquals("No product provided in wishlist", thrownException.getMessage());
    }


}
