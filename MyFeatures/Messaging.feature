Feature: Order Management

 Scenario: Customer sends a message to store owner
  Given That Customer with email "order.user@gmail.com" is logged in
  When The Customer sends a message "How do you make cake" to owner with email "owner@gmail.com"
  Then it's received by the owner

 Scenario: Customer sends a message to supplier
  Given That Customer with email "order.user@gmail.com" is logged in
  When The Customer sends a message "How do you grow sugar" to supplier with email "supplier@gmail.com"
  Then it's received by the supplier

 Scenario: Store owner reply to message received
  Given The owner with email "owner@gmail.com"  is logged in
  When the owner reply with "Here is how I do it" to message received from user with email "order.user@gmail.com"
  Then the reply is sent to user

 Scenario: Supplier reply to message received
  Given The supplier with email "supplier@gmail.com"  is logged in
  When the supplier reply with "this is how I do it" to message received from user with email "order.user@gmail.com"
  Then the reply is sent to user

 Scenario: User reply to message received
  Given That Customer with email "order.user@gmail.com" is logged in
  When the user reply with "Ok" to message received from owner with email "owner@gmail.com"
  Then the reply is sent to owner

 Scenario: User looks at all messages sent
  Given That Customer with email "order.user@gmail.com" is logged in
  When The user chooses the show all sent messages option
  Then The messages are shown

 Scenario: User looks at all messages sent to specific user
  Given That Customer with email "order.user@gmail.com" is logged in
  When The user chooses user with email "owner@gmail.com" to see message history
  Then The messages are shown

 Scenario: User looks at all messages received
  Given That Customer with email "order.user@gmail.com" is logged in
  When The user chooses the show all received messages option
  Then The messages are shown
