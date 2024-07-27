Feature: Products Management

  Scenario: Successfully adding products
    Given that the user is logged in and user is store owner or raw material supplier
    When The user add a new product with full info
    Then The product should be added successfully