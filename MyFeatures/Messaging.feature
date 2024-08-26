Feature: Messaging

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

  Scenario: User tries to get a message by id number
   Given That Customer with email "order.user@gmail.com" is logged in
   When The user tries to get a message by id "1"
   Then The message is shown

 Scenario: User tries to get a message by invalid id number
  Given That Customer with email "order.user@gmail.com" is logged in
  When The user tries to get a message by invalid id "5000"
  Then The message wont be shown

  Scenario: Beneficiary user orders a special request
   Given That the user with email "order.user@gmail.com" is logged in
   When The user makes a special request to the email "momanani2017@gmail.com" and  the message "I want to request a juicer cake"
   Then A notification is made

 Scenario: User checks all notifications
  Given The user with email "order.store@gmail.com" is logged in
  When The user checks all their notifications
  Then All notifications for "order.store@gmail.com" are displayed

 Scenario: User marks a notification as read
  Given The user with email "order.store@gmail.com" is logged in
  When The user checks his notifications
  And The user marks the notification with ID "1" as read
  Then The notification with ID "1" should be marked as read

 Scenario: User deletes a notification
  Given The user with email "order.store@gmail.com" is logged in
  When The user checks his notifications
  And The user deletes the notification with ID "2"
  Then The notification with ID "2" should no longer exist

  Scenario: User reading a notification
   Given The user with email "order.store@gmail.com" is logged in
   When The user checks his unread notifications
   Then Number of unread notifications should be returned

# Scenario: User checks unread notifications with a null database connection
#  Given The user with email "order.store@gmail.com" is logged in
#  When The user checks his unread notifications and the connection is null
#  Then The number of unread notifications should not be returned
