package com.br.api.wishlist.cucumber;

import com.br.api.wishlist.domain.model.ClientPageQuery;
import com.br.api.wishlist.domain.model.Product;
import com.br.api.wishlist.domain.model.Wishlist;
import io.cucumber.java.en.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetAllProductsWishilist {

    private List<Product> fetchedProducts;
    private Wishlist wishlist;
    private Exception exception;

    @Given("a client with ID {string} with an wishlist which has {int} products")
    public void client_with_wishlist_with_n_products(String clientId, int count) {
        wishlist = new Wishlist(clientId);
        for (int i = 0; i < count; i++) {
            wishlist.addProduct(new Product(String.format("%024d", i)));
        }
        exception = null;
    }

    @Given("a client with ID {string} with no wishlist to find all")
    public void client_with_no_wishlist_to_find_all(String clientId) {
        wishlist = null;
        exception = null;
    }

    @Then("should return to me {int} products")
    public void should_return_products(int expectedCount) {
        assertNull(exception, () -> "Expected no exception, but got one.");
        assertNotNull(fetchedProducts);
        assertEquals(expectedCount, fetchedProducts.size());
    }

    @Then("after trying searching all should give me an exception saying {string}")
    public void should_throw_get_all_exception(String expectedMessage) {
        assertNotNull(exception, "Expected an exception to be thrown.");
        assertEquals(expectedMessage, exception.getMessage());
    }

    @When("I try to get the products from the client ID {string} from page {int}")
    public void try_to_get_products_from_page(String clientId, int page) {
        try {
            ClientPageQuery query = new ClientPageQuery(clientId, page);

            if (wishlist == null || !wishlist.getClientId().equals(query.getClientId())) {
                throw new IllegalStateException("There is no withlist to this client to find all");
            }

            int pageSize = 3;
            int from = query.getPage() * pageSize;
            int to = Math.min(from + pageSize, wishlist.getProducts().size());

            if (from >= wishlist.getProducts().size()) {
                throw new IllegalArgumentException("There is no more pages left with products");
            }

            fetchedProducts = wishlist.getProducts().subList(from, to);

        } catch (Exception e) {
            exception = e;
        }
    }


}
