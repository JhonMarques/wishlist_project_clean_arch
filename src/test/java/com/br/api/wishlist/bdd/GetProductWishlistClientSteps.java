package com.br.api.wishlist.bdd;

import com.br.api.wishlist.domain.exceptions.WishlistDoesNotExistException;
import com.br.api.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.wishlist.application.usecases.GetProductWishlistClient;
import com.br.api.wishlist.domain.validators.objectid.WishlistObjectIdValidator;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetProductWishlistClientSteps {

    private IWishlistRepository wishlistRepository;
    private WishlistObjectIdValidator objectIdValidator;
    private GetProductWishlistClient getProductWishlistClient;

    private String clientId;
    private String productId;
    private boolean result;
    private Exception thrownException;

    @Before
    public void setup() {
        wishlistRepository = mock(IWishlistRepository.class);
        objectIdValidator = mock(WishlistObjectIdValidator.class);
        getProductWishlistClient = new GetProductWishlistClient(wishlistRepository, objectIdValidator);
    }

    @Given("the client with ID {string} and product ID {string}")
    public void the_client_with_ids(String clientId, String productId) {
        this.clientId = clientId;
        this.productId = productId;
    }

    @Given("the wishlist exists for the client")
    public void the_wishlist_exists_for_client() {
        when(wishlistRepository.existsByClientId(clientId)).thenReturn(true);
    }

    @Given("the wishlist does not exist for the client")
    public void the_wishlist_does_not_exist_for_client() {
        when(wishlistRepository.existsByClientId(clientId)).thenReturn(false);
    }

    @Given("the product exists in the wishlist")
    public void the_product_exists_in_the_wishlist() {
        when(wishlistRepository.productExistInWishlistByClientId(clientId, productId)).thenReturn(true);
    }

    @When("checking if the product exists in the wishlist")
    public void checking_product_existence() {
        try {
            result = getProductWishlistClient.call(clientId, productId);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("the result should be true")
    public void the_result_should_be_true() {
        assertTrue(result);
    }

    @Then("an exception should be thrown indicating wishlist not found")
    public void exception_should_be_thrown() {
        assertNotNull(thrownException);
        assertTrue(thrownException instanceof WishlistDoesNotExistException);
        assertEquals("This wishlist was not found", thrownException.getMessage());
    }
}
