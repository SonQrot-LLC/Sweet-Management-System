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


