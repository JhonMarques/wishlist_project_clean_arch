Feature: Retrieve all products from a client's wishlist

  Scenario: Successfully retrieving a paginated list of products from an existing wishlist
    Given the client "6657a735bcf1e9446c5f50ee" has a wishlist with products "6657a735bcf1e9446c5f51001", "6657a735bcf1e9446c5f51002", "6657a735bcf1e9446c5f51003"
    And the client requests page number 1
    When the system retrieves the wishlist products
    Then the result should contain the list of products
    And pagination should offset by 5 items per page

  Scenario: Client does not have a wishlist
    Given the client "6657a735bcf1e9446c5f51234" does not have a wishlist and requests page number 1
    When the system retrieves the wishlist products
    Then an exception should be thrown indicating the wishlist does not exist


  Scenario: Page number is null
    Given the client "6657a735bcf1e9446c5f51077" sends a request with null page value
    When the system retrieves the wishlist products
    Then an exception should be thrown indicating the page value is required

