Feature: Wishlist product removal

  Scenario: Remove a product from an already existing wishlist
    Given a client with ID "tmz480kC80Rgoit81l3ktrwb" with an wishlist which has two products ID "507f1f77bcf86cd799439011" and "507f1f77bcf86cd799439012"
    When I try to remove the product of ID "507f1f77bcf86cd799439011" from the wishlist from the client ID "tmz480kC80Rgoit81l3ktrwb"
    Then the wishlist from the client ID "tmz480kC80Rgoit81l3ktrwb" should have 1 products now

  Scenario: Remove a product from a non-existing wishlist
    Given a client with ID "tmz480kC80Rgoit81l3ktrwb" with no wishlist
    When I try to remove the product of ID "507f1f77bcf86cd799439011" from the wishlist from the client ID "tmz480kC80Rgoit81l3ktrwb"
    Then after trying removing should give me an exception saying "There is no withlist to this client to delete"

  Scenario: Remove a non-existent product from an already existing wishlist
    Given a client with ID "tmz480kC80Rgoit81l3ktrwb" with an wishlist which has two products ID "507f1f77bcf86cd799439011" and "507f1f77bcf86cd799439012"
    When I try to remove the product of ID "507f1f77bcf86cd799439099" from the wishlist from the client ID "tmz480kC80Rgoit81l3ktrwb"
    Then after trying removing should give me an exception saying "This product do not exist in this wishlist to remove it"

  Scenario: Remove a product from an already existing wishlist with only one product
    Given a client with ID "tmz480kC80Rgoit81l3ktrwb" with an wishlist which has one product ID "507f1f77bcf86cd799439011"
    When I try to remove the product of ID "507f1f77bcf86cd799439011" from the wishlist from the client ID "tmz480kC80Rgoit81l3ktrwb"
    Then the wishlist from the client ID "tmz480kC80Rgoit81l3ktrwb" should not exists
