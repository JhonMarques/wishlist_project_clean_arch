Feature: Wishlist product check

  Scenario: Check if an product exists in an wishlist
    Given a client with ID "5e3fabcd6789123456789013" with an wishlist which has the product ID "5e2fbd88db12345678901245"
    When I try to check the product of ID "5e2fbd88db12345678901245" from the wishlist from the client ID "5e3fabcd6789123456789013"
    Then the check should be true

  Scenario: Check if an product exists in an wishlist but it does not
    Given a client with ID "5e3fabcd6789123456789013" with an wishlist which has the product ID "5e2fbd88db12345678901245"
    When I try to check the product of ID "5e2fbd88db12345678901246" from the wishlist from the client ID "5e3fabcd6789123456789013"
    Then the check should be false

  Scenario: Check if an product exists in an inexisting wishlist
    Given a client with ID "5e3fabcd6789123456789013" with an wishlist which has the product ID "5e2fbd88db12345678901245"
    When I try to check the product of ID "5e2fbd88db12345678901245" from the wishlist from the client ID "5e3fabcd6789123456789090"
    Then after trying searching should give me an exception saying "This wishlist was not found"
