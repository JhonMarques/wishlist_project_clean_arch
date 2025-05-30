Feature: Create wishlist with product

  Scenario: Create a new wishlist with one product
    Given the client "64b82feca485c812e4bcd456" does not have a wishlist
    And the client sends a wishlist with productId "64b82feca485c812e4bcd123"
    When the wishlist is created
    Then the wishlist should be saved
    And the response should indicate that the wishlist was newly created

  Scenario: Add product to existing wishlist
    Given the client "64b82feca485c812e4bcd456" already has a wishlist
    And the client sends a wishlist with a new productId "64b82feca485c812e4bcd456"
    When the wishlist is created
    Then the product should be validated and added to the existing wishlist
    And the response should indicate the wishlist was updated

  Scenario: Attempt to create wishlist without product
    Given the client "64b82feca485c812e4bcd456" sends an empty wishlist
    When the wishlist is created
    Then an exception should be thrown indicating no product was provided

