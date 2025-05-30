Feature: Delete Wishlist Product
  As a system user
  I want to delete a product from a wishlist
  So that the wishlist is updated or deleted accordingly

  Scenario: Deleting a product when it is the only one in the wishlist
    Given the client "6657a735bcf1e9446c5f50ee" has a wishlist with one product "6657a735bcf1e9446c5f50ef"
    When the client deletes the product "6657a735bcf1e9446c5f50ef"
    Then the wishlist should be deleted

  Scenario: Deleting a product when there are multiple products in the wishlist
    Given the client "6657a735bcf1e9446c5f50ee" has a wishlist with products "6657a735bcf1e9446c5f50ef" and "6657a735bcf1e9446c5f50f0"
    When the client deletes the product "6657a735bcf1e9446c5f50ef"
    Then the product should be removed and the wishlist should be updated

  Scenario: Attempting to delete a product not present in the wishlist
    Given the client "6657a735bcf1e9446c5f50ee" has a wishlist with product "6657a735bcf1e9446c5f50f1"
    When the client tries to delete the product "6657a735bcf1e9446c5f50f2"
    Then an exception should be thrown indicating the product cannot be deleted
