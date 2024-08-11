Feature: Monitoring And Reporting Management

  Scenario: Retrieve best-selling products successfully
    Given the database connection is established
    When I retrieve the best-selling products
    Then the best-selling products should be displayed successfully

  Scenario: Retrieve store profits successfully
    Given the database connection is established
    When I retrieve the store profits
    Then the store profits should be displayed successfully

  Scenario: Retrieve users by city successfully
    Given the database connection is established
    When I retrieve users by city
    Then the users by city should be displayed successfully

  Scenario: Handle SQL exception when retrieving best-selling products
    Given the database connection is established with a bad connection
    When I retrieve the best-selling products
    Then the retrieval should fail

  Scenario: Handle SQL exception when retrieving store profits
    Given the database connection is established with a bad connection
    When I retrieve the store profits
    Then the retrieval should fail

  Scenario: Handle SQL exception when retrieving users by city
    Given the database connection is established with a bad connection
    When I retrieve users by city
    Then the retrieval should fail
