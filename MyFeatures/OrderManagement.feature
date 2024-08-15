Feature: Order Management

  Scenario: Making a valid new order
    Given User is logged in with email "order.user@gmail.com" and password "321"
    When The user make a new order email "order.user@gmail.com" and orderStatus "pending"
    Then The order should be successfully created

  Scenario: Successfully updating order
    Given User is logged in with email "order.user@gmail.com" and password "321"
    When The store owner edits an order with id "1"
    And updates the status to "processed"
    And updates total amount to "0.0"
    Then order is updated successfully

  Scenario: Successfully deleting order
    Given User is logged in with email "order.user@gmail.com" and password "321"
    When The user deletes order with ID "4000"
    Then The order should be deleted successfully

  Scenario: Invalid order update
    Given User is logged in with email "order.user@gmail.com" and password "321"
    When The user edits an order with ID "1"
    And The user chooses invalid order updateType
    Then The order update should fail

  Scenario: Getting group of Orders
    Given User is logged in with email "order.user@gmail.com" and password "321"
    When The user try to  get orders by store id "2"
    Then it should return the orders without exceptions
    When The user try to  get orders by user email "order.user@gmail.com"
    Then it should return the orders without exceptions
    When The user try to  get orders by user email "order.user@gmail.com" but there is no connection
    Then it should return an exceptions
    When The user try to  get orders by store id "2" but there is no connection
    Then it should return an exceptions


  Scenario: An order to a product is created
    Given The order_id "1" is made to purchase the product_id "1" and the product and Order exists in the database
    When The quantity is "3" and the price is "18.00"
    Then The orderItem should be successfully created

  Scenario: Successfully updating order item quantity
    Given The order_id "1" is made to purchase the product_id "1" and the product and Order exists in the database
    When The user edits the order item to update the quantity to "5"
    Then The orderItem quantity should be successfully updated

  Scenario: Successfully updating order item price
    Given The order_id "1" is made to purchase the product_id "1" and the product and Order exists in the database
    When The user edits the order item to update the price to "20.00"
    Then The orderItem price should be successfully updated

  Scenario: Successfully deleting order item
    Given The order_id "1" is made to purchase the product_id "1" and the product and Order exists in the database
    When The user deletes the order item
    Then The orderItem should be deleted successfully

  Scenario: Invalid order item update
    Given The order_id "1" is made to purchase the product_id "1" and the product and Order exists in the database
    When The user edits the order item with an invalid update type
    Then The orderItem update should fail
