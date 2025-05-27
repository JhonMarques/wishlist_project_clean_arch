package com.br.api.wishlist.cucumber;

import com.br.api.wishlist.domain.model.Product;
import com.br.api.wishlist.domain.model.Wishlist;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetProdutWishilist {

    private Wishlist wishlist;
    private Exception exception;
    private boolean productExists;

    @Given("a client with ID {string} with an wishlist which has the product ID {string}")
    public void client_with_wishlist_with_product(String clientId, String productId) {
        wishlist = new Wishlist(clientId);
        wishlist.addProduct(new Product(productId));
        exception = null;
        productExists = false;
    }

    @When("I try to check the product of ID {string} from the wishlist from the client ID {string}")
    public void try_to_check_product_in_wishlist(String productId, String clientId) {
        try {
            if (wishlist == null || !wishlist.getClientId().equals(clientId)) {
                throw new IllegalStateException("This wishlist was not found");
            }
            productExists = wishlist.containsProduct(productId);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the check should be true")
    public void check_should_be_true() {
        assertNull(exception, "Expected no exception to be thrown.");
        assertTrue(productExists, "Expected product to exist in wishlist.");
    }

    @Then("the check should be false")
    public void check_should_be_false() {
        assertNull(exception, "Expected no exception to be thrown.");
        assertFalse(productExists, "Expected product not to exist in wishlist.");
    }

    @Then("after trying searching should give me an exception saying {string}")
    public void check_should_throw_exception(String expectedMessage) {
        assertNotNull(exception, "Expected an exception to be thrown.");
        assertEquals(expectedMessage, exception.getMessage());
    }
}
