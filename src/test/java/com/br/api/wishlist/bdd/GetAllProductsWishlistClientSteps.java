package com.br.api.wishlist.bdd;

import com.br.api.wishlist.domain.converters.FindAllWishlistValidationParamsConverter;
import com.br.api.wishlist.domain.entities.WishlistProduct;
import com.br.api.wishlist.domain.entities.WishlistPage;
import com.br.api.wishlist.domain.repositories.IWishlistRepository;
import com.br.api.wishlist.application.usecases.GetAllProductsWishlistClient;
import com.br.api.wishlist.domain.validators.findall.WishlistFindAllValidator;
import com.br.api.wishlist.domain.validators.objectid.WishlistObjectIdValidator;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetAllProductsWishlistClientSteps {

    private IWishlistRepository wishlistRepository;
    private WishlistObjectIdValidator objectIdValidator;
    private WishlistFindAllValidator findAllValidator;
    private FindAllWishlistValidationParamsConverter converter;
    private GetAllProductsWishlistClient useCase;

    private WishlistPage request;
    private List<WishlistProduct> result;
    private Exception thrownException;

    private String clientId;
    private Integer requestedPage;

    @Before
    public void setup() {
        wishlistRepository = mock(IWishlistRepository.class);
        objectIdValidator = mock(WishlistObjectIdValidator.class);
        findAllValidator = mock(WishlistFindAllValidator.class);
        converter = mock(FindAllWishlistValidationParamsConverter.class);
        useCase = new GetAllProductsWishlistClient(wishlistRepository, objectIdValidator, findAllValidator, converter);
    }

    @Given("the client {string} has a wishlist with products {string}, {string}, {string}")
    public void the_client_has_a_wishlist_with_products(String clientId, String product1, String product2, String product3) {
        this.clientId = clientId;
        WishlistProduct p1 = new WishlistProduct(new ObjectId("6657a735bcf1e9446c5f5101"));
        WishlistProduct p2 = new WishlistProduct(new ObjectId("6657a735bcf1e9446c5f5102"));
        WishlistProduct p3 = new WishlistProduct(new ObjectId("6657a735bcf1e9446c5f5103"));
        List<WishlistProduct> products = List.of(p1, p2, p3);

        when(wishlistRepository.getAllProductsWishlistClient(any())).thenReturn(products);
        when(wishlistRepository.existsByClientId(clientId)).thenReturn(true);
    }

    @Given("the client requests page number {int}")
    public void the_client_requests_page_number(Integer page) {
        this.requestedPage = page;
    }

    @When("the system retrieves the wishlist products")
    public void the_system_retrieves_the_wishlist_products() {
        try {
            request = new WishlistPage();
            request.setClientId(clientId);
            request.setPage(requestedPage);

            result = useCase.call(request);
        } catch (Exception e) {
            thrownException = e;
        }
    }


    @Then("the result should contain the list of products")
    public void the_result_should_contain_the_list_of_products() {
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Then("pagination should offset by {int} items per page")
    public void pagination_should_offset_by_items_per_page(Integer itemsPerPage) {
        int expectedStartIndex = (requestedPage - 1) * itemsPerPage;
        assertTrue(result.size() <= itemsPerPage);
    }

    @Given("the client {string} sends a request with null page value")
    public void the_client_sends_a_request_with_null_page_value(String clientId) {
        request = new WishlistPage();
        request.setClientId(clientId);
        request.setPage(null);
    }

    @Then("an exception should be thrown indicating the page value is required")
    public void an_exception_should_be_thrown_indicating_the_page_value_is_required() {
        Assertions.assertNotNull(thrownException);
        Assertions.assertTrue(thrownException instanceof IllegalArgumentException);
        Assertions.assertEquals("Client ID and page are required.", thrownException.getMessage());
    }

    @Then("an exception should be thrown indicating the wishlist does not exist")
    public void an_exception_should_be_thrown_indicating_the_wishlist_does_not_exist() {
        Assertions.assertNotNull(thrownException);
        Assertions.assertTrue(thrownException instanceof IllegalArgumentException);
        Assertions.assertEquals("Wishlist not found", thrownException.getMessage());
    }

    @Given("the client {string} does not have a wishlist and requests page number {int}")
    public void the_client_does_not_have_a_wishlist_and_requests_page_number(String clientId, Integer page) {
        this.clientId = clientId;
        this.requestedPage = page;

        request = new WishlistPage();
        request.setClientId(clientId);
        request.setPage(page);

        when(wishlistRepository.existsByClientId(clientId)).thenReturn(false);
        when(wishlistRepository.getAllProductsWishlistClient(any())).thenReturn(Collections.emptyList());
    }


}
