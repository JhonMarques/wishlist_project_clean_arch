package com.br.api.wishlist.cucumber;

import com.br.api.wishlist.domain.model.Product;
import com.br.api.wishlist.domain.model.Wishlist;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteStepDefinitions {

    private Wishlist wishlist;
    private Exception exception;

    @Given("a client with ID {string} with an wishlist which has two products ID {string} and {string}")
    public void client_with_wishlist_with_two_products(String clientId, String productId1, String productId2) {
        wishlist = new Wishlist(clientId);
        wishlist.addProduct(new Product(productId1));
        wishlist.addProduct(new Product(productId2));
        exception = null;
    }

    @Given("a client with ID {string} with no wishlist")
    public void client_with_no_wishlist(String clientId) {
        wishlist = null;
        exception = null;
    }

    @Given("a client with ID {string} with an wishlist which has one product ID {string}")
    public void client_with_wishlist_with_one_product(String clientId, String productId) {
        wishlist = new Wishlist(clientId);
        wishlist.addProduct(new Product(productId));
        exception = null;
    }

    @When("I try to remove the product of ID {string} from the wishlist from the client ID {string}")
    public void try_to_remove_product(String productId, String clientId) {
        try {
            if (wishlist == null || !wishlist.getClientId().equals(clientId)) {
                throw new IllegalStateException("There is no withlist to this client to delete");
            }

            if (!wishlist.containsProduct(productId)) {
                throw new IllegalArgumentException("This product do not exist in this wishlist to remove it");
            }

            wishlist.removeProduct(productId);

            if (wishlist.getProducts().isEmpty()) {
                wishlist = null; // Simula remoção completa da wishlist
            }

        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("after trying removing should give me an exception saying {string}")
    public void should_throw_removal_exception(String expectedMessage) {
        assertNotNull(exception, "Expected an exception to be thrown.");
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Then("the wishlist from the client ID {string} should not exists")
    public void wishlist_should_not_exist(String clientId) {
        assertNull(wishlist, "Expected the wishlist to have been removed completely.");
    }

    @Then("the wishlist from the client ID {string} should have {int} products now")
    public void the_wishlist_from_the_client_id_should_have_products_now(String clientId, int expectedCount) {
        assertNotNull(wishlist, "Expected wishlist to exist.");
        assertEquals(clientId, wishlist.getClientId());
        assertEquals(expectedCount, wishlist.getProducts().size());
    }


}
