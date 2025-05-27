package com.br.api.wishlist.cucumber;

import com.br.api.wishlist.domain.model.Product;
import com.br.api.wishlist.domain.model.Wishlist;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class CreateStepDefinitions {

    private Wishlist wishlist;
    private Exception exception;

    @Given("a client with ID {string} does not have a wishlist")
    public void a_client_with_id_does_not_have_a_wishlist(String clientId) {
        wishlist = new Wishlist(clientId);
        exception = null;
    }

    @Given("a client with ID {string} that has a wishlist with the product ID {string}")
    public void a_client_with_id_that_has_a_wishlist_with_the_product_id(String clientId, String productId) {
        wishlist = new Wishlist(clientId);
        wishlist.addProduct(new Product(productId));
        exception = null;
    }

    @Given("a client with ID {string} that has a limit of products")
    public void a_client_with_id_that_has_a_limit_of_products(String clientId) {
        wishlist = new Wishlist(clientId);
        for (int i = 0; i < 20; i++) {
            wishlist.addProduct(new Product(String.format("%024d", i)));
        }
        exception = null;
    }

    @When("I try to create a wishlist for the client with ID {string} and product ID {string}")
    public void i_try_to_create_a_wishlist_for_the_client_with_id_and_product_id(String clientId, String productId) {
        try {
            if (wishlist == null) {
                wishlist = new Wishlist(clientId);
            }
            wishlist.addProduct(new Product(productId));
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("a new wishlist should be created for the client ID {string} with no errors")
    public void a_new_wishlist_should_be_created_for_the_client_id_with_no_errors(String clientId) {
        assertNotNull(wishlist);
        assertEquals(clientId, wishlist.getClientId());
        assertNull(exception, "An unexpected exception occurred: " + (exception != null ? exception.getMessage() : ""));
    }

    @Then("after trying saving should give me an exception saying {string}")
    public void after_trying_saving_should_give_me_an_exception_saying(String expectedMessage) {
        assertNotNull(exception, "Expected an exception but none was thrown.");
        assertEquals(expectedMessage, exception.getMessage());
    }
}
