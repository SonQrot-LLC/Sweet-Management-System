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
#
#  Scenario: invalid sql connection
#    Given User is logged in with email "order.user@gmail.com" and password "321"
#    When The user try to  get orders by store id "2" and the there is sql connection problem
#    When The user try to  get orders by user email "order.user@gmail.com" and the there is sql connection problem
#    Then it should fail

  Scenario: An order to a product is created
    Given The order_id "1" is made to purchase the product_id "1" and the product and Order exists in the database
    When The quantity is "3" and the price is "18.00"
    Then The orderItem should be successfully created

#  Scenario: An order to a non existing product
#    Given The order_id "1" is made to purchase the product_id "999"
#    When the product doesn't exist in the database
#    Then The orderItem should not be created
#
#  Scenario: Updating an orderItem
#    Given That the orderItem is "1" and The product is "1" and The order is "1"
#    When The quantity is "1" and the quantity is updated to "2"
#    Then The price is doubled
#
#  Scenario: Invalid update to orderItem
#    Given That the orderItem is "1" and The product is "1" and The order is "1"
#    When The quantity is "1" and the quantity is updated to "0"
#    Then Will result in a warning
#
#  Scenario: Deleting an orderItem
#    Given That the orderItem is "2"
#    When The order item exists in  the database
#    Then  OrderItem should be deleted
#
#  Scenario: Retrieving Order Items by order_item_id
#    Given a valid connection to the database
#    And an order with ID "1" exists in the database
#    When the user retrieves order items for order_item_id "1"
#    Then the system should return a list of order items without exceptions
#    And the list should contain order items with valid details like "order_item_id", "order_id", "product_id", "quantity", and "price"
#    And the total number of order items should match the expected count
#
#
#
