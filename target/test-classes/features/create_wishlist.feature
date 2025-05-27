Feature: Wishlist creation

  Scenario: Create a new wishlist when none exists for a client
    Given a client with ID "tmz480kC80Rgoit81l3ktrwb" does not have a wishlist
    When I try to create a wishlist for the client with ID "tmz480kC80Rgoit81l3ktrwb" and product ID "507f1f77bcf86cd799439011"
    Then a new wishlist should be created for the client ID "tmz480kC80Rgoit81l3ktrwb" with no errors

  Scenario: Create a new wishlist when one already exists for a client and tried to add a duplicate product
    Given a client with ID "tmz480kC80Rgoit81l3ktrwb" that has a wishlist with the product ID "507f1f77bcf86cd799439011"
    When I try to create a wishlist for the client with ID "tmz480kC80Rgoit81l3ktrwb" and product ID "507f1f77bcf86cd799439011"
    Then after trying saving should give me an exception saying "Product already exists in wishlist."

  Scenario: Create a new wishlist when one already exists for a client and it already has the limit od products
    Given a client with ID "tmz480kC80Rgoit81l3ktrwb" that has a limit of products
    When I try to create a wishlist for the client with ID "tmz480kC80Rgoit81l3ktrwb" and product ID "507f1f77bcf86cd799439011"
    Then after trying saving should give me an exception saying "Wishlist cannot contain more than 20 products."

  Scenario: Create a new wishlist when one already exists for a client
    Given a client with ID "tmz480kC80Rgoit81l3ktrwb" that has a wishlist with the product ID "507f1f77bcf86cd799439011"
    When I try to create a wishlist for the client with ID "tmz480kC80Rgoit81l3ktrwb" and product ID "507f1f77bcf86cd799439012"
    Then a new wishlist should be created for the client ID "tmz480kC80Rgoit81l3ktrwb" with no errors
