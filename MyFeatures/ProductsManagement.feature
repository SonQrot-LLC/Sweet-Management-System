Feature: Products Management

  Scenario: Successfully adding products
    Given that the user is logged in and user is store owner or raw material supplier
    When The user add a new product with name "cheesecake" And description "Big juicy cake" And price "6" And stock "30" And expiry date "2024-12-28" Then The product should be added successfully
    Then The product should be added successfully to the database

  Scenario: Successfully updating products
    Given that the user is logged in and user is store owner or raw material supplier
    When The user edits a product with ID "1"
    And sets product name to "chocolate cake"
    And sets product description to "Dark chocolate cake"
    And sets product price to "7"
    And sets product stock to "25"
    And sets product expiry date to "2025-01-01"
    Then The product should be updated successfully

  Scenario: Successfully deleting products
    Given that the user is logged in and user is store owner or raw material supplier
    When The user deletes product with ID "4000"
    Then The product should be deleted successfully

  Scenario: Invalid product update
    Given that the user is logged in and user is store owner or raw material supplier
    When The user edits a product with ID "1"
    And The user chooses invalid updateType
    Then The product update should fail


  Scenario: Invalid product addition
    Given that the user is logged in and user is store owner or raw material supplier
    When The user adds an already existing product
    Then The product shouldn't be added

#  Scenario: View product sales and profit
#    Given that the user is logged in and user is store owner or raw material supplier
#    When the user
#    Then product sales should be displayed

  Scenario: View best selling product
    Given that the user is logged in and user is store owner or raw material supplier
    When the user checks the best selling product in store
    Then the product with the most sales should appear

  Scenario: Suggest product discount
    Given that the user is logged in and user is store owner or raw material supplier
    When the user asks for discount suggestion
    Then the products close expiry dates should appear

  Scenario: Add discount value
    Given that the user is logged in and user is store owner or raw material supplier
    When The user chooses a suggested product with id "4" for discount and adds value "10" to the discount
    Then The discount value is updated for this product


    Scenario: Product trying to get all products
      Given That the user is logged in with a user "ahmad123@gmail.com" and password "777"
      When The user tries to get all products
      Then The products should be shown successfully

  Scenario: User trying to get all products
    Given That the user is logged in with a user "ahmad123@gmail.com" and password "777"
    When The user tries to get all products but there is no connection
    Then The products should not be shown

  Scenario: User trying to get all products
    Given That the user is logged in with a user "ahmad123@gmail.com" and password "777"
    When The user tries to get all products from a store by store id "1"
    Then The products should be shown successfully

  Scenario: User trying to get all products
    Given That the user is logged in with a user "ahmad123@gmail.com" and password "777"
    When The user tries to get all products from a store by Search "Milk"
    Then The products should be shown successfully


    Scenario: Store trying to get all of its products
      Given That the user is logged using email "salam@hawa.com" and password "222"
      When The user tries to get all of its products
      Then The products should be shown successfully

