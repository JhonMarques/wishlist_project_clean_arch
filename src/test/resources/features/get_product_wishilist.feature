Feature: Check if a product exists in the client's wishlist

  Scenario: Product exists in existing wishlist
    Given the client with ID "60d0fe4f5311236168a109ca" and product ID "60d0fe4f5311236168a109cb"
    And the wishlist exists for the client
    And the product exists in the wishlist
    When checking if the product exists in the wishlist
    Then the result should be true

  Scenario: Wishlist does not exist for the client
    Given the client with ID "60d0fe4f5311236168a109ca" and product ID "60d0fe4f5311236168a109cb"
    And the wishlist does not exist for the client
    When checking if the product exists in the wishlist
    Then an exception should be thrown indicating wishlist not found
