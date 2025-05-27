Feature: Wishlist product get all

  Scenario: Trying to return multiple products from existing wishlist
    Given a client with ID "5e3fabcd6789123456789013" with an wishlist which has 3 products
    When I try to get the products from the client ID "5e3fabcd6789123456789013" from page 0
    Then should return to me 3 products

  Scenario: Trying to return multiple products from an inexisting wishlist
    Given a client with ID "5e3fabcd6789123456789013" with no wishlist to find all
    When I try to get the products from the client ID "5e3fabcd6789123456789013" from page 0
    Then after trying searching all should give me an exception saying "There is no withlist to this client to find all"

  Scenario: Trying to teturn multiple products from existing wishlist with many products
    Given a client with ID "5e3fabcd6789123456789013" with an wishlist which has 5 products
    When I try to get the products from the client ID "5e3fabcd6789123456789013" from page 2
    Then after trying searching all should give me an exception saying "There is no more pages left with products"
