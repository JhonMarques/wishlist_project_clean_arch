package com.br.api.wishlist.bdd;

import com.br.api.wishlist.domain.converters.DeleteWishlistValidationParamsConverter;
import com.br.api.wishlist.domain.entities.Product;
import com.br.api.wishlist.domain.entities.Wishlist;
import com.br.api.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.wishlist.domain.usecases.DeleteWishlistProduct;
import com.br.api.wishlist.domain.validators.delete.WishlistDeleteValidator;
import com.br.api.wishlist.domain.validators.objectid.WishlistObjectIdValidator;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.bson.types.ObjectId;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteWishlistProductSteps {

    private IWishlistRepository wishlistRepository;
    private WishlistDeleteValidator deleteValidator;
    private WishlistObjectIdValidator objectIdValidator;
    private DeleteWishlistValidationParamsConverter validationParamsConverter;
    private DeleteWishlistProduct deleteWishlistProduct;

    private Wishlist wishlist;
    private String clientId;
    private String productId;
    private Exception thrownException;

    @Before
    public void setup() {
        wishlistRepository = mock(IWishlistRepository.class);
        deleteValidator = mock(WishlistDeleteValidator.class);
        objectIdValidator = mock(WishlistObjectIdValidator.class);
        validationParamsConverter = mock(DeleteWishlistValidationParamsConverter.class);
        deleteWishlistProduct = new DeleteWishlistProduct(wishlistRepository, deleteValidator, objectIdValidator, validationParamsConverter);
    }

    @Given("a client {string} with a wishlist containing the product {string}")
    public void a_client_with_a_wishlist_containing_the_product(String clientId, String productId) {
        this.clientId = clientId;
        this.productId = productId;
        Product product = new Product(new ObjectId(productId));
        wishlist = new Wishlist();
        wishlist.setId(String.valueOf(new ObjectId()));
        wishlist.setClientId(clientId);
        wishlist.setProducts(new ArrayList<>(List.of(product)));
        when(wishlistRepository.findByClientId(clientId)).thenReturn(wishlist);
    }

    @When("the product is deleted from the wishlist")
    public void the_product_is_deleted_from_the_wishlist() {
        try {
            deleteWishlistProduct.call(clientId, productId);
        } catch (Exception e) {
            this.thrownException = e;
        }
    }

    @Then("the wishlist should be deleted")
    public void the_wishlist_should_be_deleted() {
        verify(wishlistRepository, times(1)).deleteWishlistById(wishlist.getId());
    }

    @Given("a client {string} with multiple products including {string}")
    public void a_client_with_multiple_products_including(String clientId, String productId) {
        this.clientId = clientId;
        this.productId = productId;
        Product targetProduct = new Product(new ObjectId(productId));
        Product otherProduct = new Product(new ObjectId());
        wishlist = new Wishlist();
        wishlist.setId(String.valueOf(new ObjectId()));
        wishlist.setClientId(clientId);
        wishlist.setProducts(new ArrayList<>(List.of(targetProduct, otherProduct)));
        when(wishlistRepository.findByClientId(clientId)).thenReturn(wishlist);
    }

    @Then("the product should be removed and wishlist updated")
    public void the_product_should_be_removed_and_wishlist_updated() {
        verify(wishlistRepository, times(1)).saveWishlistProduct(argThat(w ->
                w.getProducts().stream().noneMatch(p -> p.getProductId().equals(productId))));
    }

    @Given("a client {string} with no product {string} in the wishlist")
    public void a_client_with_no_product_in_wishlist(String clientId, String productId) {
        this.clientId = clientId;
        this.productId = productId;
        wishlist = new Wishlist();
        wishlist.setClientId(clientId);
        wishlist.setProducts(new ArrayList<>());
        when(wishlistRepository.findByClientId(clientId)).thenReturn(wishlist);
    }

    @Then("an exception should be thrown due to invalid deletion")
    public void an_exception_should_be_thrown_due_to_invalid_deletion() {
        assertNotNull(thrownException);
        verify(deleteValidator).validate(any());
    }

    @Given("the client {string} has a wishlist with one product {string}")
    public void the_client_has_a_wishlist_with_one_product(String clientId, String productId) {
        this.wishlist = new Wishlist();
        wishlist.setId("6657a735bcf1e9446c5f50aa");
        wishlist.setClientId(clientId);

        Product product = new Product(new ObjectId(productId));
        wishlist.setProducts(List.of(product));

        when(wishlistRepository.existsByClientId(clientId)).thenReturn(true);
        when(wishlistRepository.findByClientId(clientId)).thenReturn(wishlist);

        this.clientId = clientId;
        this.productId = productId;
    }


    @When("the client deletes the product {string}")
    public void the_client_deletes_the_product(String productId) {
        this.productId = productId;
        try {
            deleteWishlistProduct.call(clientId, productId);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Given("the client {string} has a wishlist with products {string} and {string}")
    public void the_client_has_a_wishlist_with_products_and(String clientId, String productId1, String productId2) {
        this.wishlist = new Wishlist();
        this.wishlist.setId("6657a735bcf1e9446c5f50aa");
        this.wishlist.setClientId(clientId);

        Product product1 = new Product(new ObjectId(productId1));
        Product product2 = new Product(new ObjectId(productId2));
        this.wishlist.setProducts(List.of(product1, product2));

        when(wishlistRepository.existsByClientId(clientId)).thenReturn(true);
        when(wishlistRepository.findByClientId(clientId)).thenReturn(this.wishlist);

        this.clientId = clientId;
    }

    @Then("the product should be removed and the wishlist should be updated")
    public void the_product_should_be_removed_and_the_wishlist_should_be_updated() {
        verify(wishlistRepository, times(1)).saveWishlistProduct(argThat(updatedWishlist ->
                updatedWishlist.getProducts().size() == 1 &&
                        updatedWishlist.getProducts().get(0).getProductId() != null &&
                        !updatedWishlist.getProducts().get(0).getProductId().equals(new ObjectId(productId))
        ));
    }

    @Then("an exception should be thrown indicating the product cannot be deleted")
    public void an_exception_should_be_thrown_indicating_the_product_cannot_be_deleted() {
        assertNotNull(thrownException);
        assertTrue(thrownException instanceof IllegalArgumentException);
        assertEquals("Product not found in wishlist", thrownException.getMessage());
    }

    @When("the client tries to delete the product {string}")
    public void the_client_tries_to_delete_the_product(String productId) {
        this.productId = productId;
        try {
            deleteWishlistProduct.call(clientId, productId);
        } catch (Exception e) {
            this.thrownException = e;
        }
    }

    @Given("the client {string} has a wishlist with product {string}")
    public void the_client_has_a_wishlist_with_product(String clientId, String productId) {
        this.clientId = clientId;
        this.wishlist = new Wishlist();
        this.wishlist.setId("6657a735bcf1e9446c5f50aa");
        this.wishlist.setClientId(clientId);
        this.wishlist.setProducts(List.of(new Product(new ObjectId(productId))));

        when(wishlistRepository.existsByClientId(clientId)).thenReturn(true);
        when(wishlistRepository.findByClientId(clientId)).thenReturn(this.wishlist);
    }

}
