Feature: AccountManagement


#  Scenario: Admin manages a user's account
#    Given I am an Admin
#    When I log in with username "admin@example.com" and password "adminpassword"
#    And I navigate to the user management section
#    Then I should see a list of all user accounts
#    When I select a user account with email "storeowner@example.com"
#    And I update the user's role to "store_owner"
#    And I save the changes
#    Then I should see a confirmation message "User account updated successfully"
#
#  Scenario: Store Owner updates business information
#    Given I am a Store Owner
#    When I log in with username "storeowner@example.com" and password "storepassword"
#    And I navigate to my account management section
#    Then I should see my business information
#    When I update the business name to "Sweet Treats"
#    And I update the business address to "123 Candy Lane"
#    And I save the changes
#    Then I should see a confirmation message "Business information updated successfully"
#
#  Scenario: Raw Material Supplier updates contact details
#    Given I am a Raw Material Supplier
#    When I log in with username "supplier@example.com" and password "supplierpassword"
#    And I navigate to my account management section
#    Then I should see my contact details
#    When I update the phone number to "123-456-7890"
#    And I update the email address to "newsupplier@example.com"
#    And I save the changes
#    Then I should see a confirmation message "Contact details updated successfully"

  Scenario: Beneficiary User updates personal account details
    Given I log in with username "ahmad123@gmail.com" and password "777" and i am a Beneficiary User
    When I update my first name to "John"
    And I update my last name to "Doe"
    And I update my address to "456 Sweet Avenue"
    And I update my phone number to "059569886"
    Then Update is successful

  Scenario:  Beneficiary User makes invalid updates
    Given I log in with username "ahmad123@gmail.com" and password "777" and i am a Beneficiary User
    When I chose an invalid updateType
    Then Update fails

  Scenario: User updates account credentials
    Given I log in with username "ahmad123@gmail.com" and password "777"
    When I update my password to "777"
    And  I update my city to "Japan"
    Then Update is successful

  Scenario:   User makes invalid account updates
    Given I log in with username "ahmad123@gmail.com" and password "777"
    When I chose an invalid account updateType
    Then Update fails

  Scenario: User deletes account
    Given I log in with username "ahmad123@gmail.com" and password "777"
    When I delete account
    Then Account is deleted

#  Scenario: Beneficiary User changes password
#    Given I am a Beneficiary User
#    When I log in with username "user@example.com" and password "userpassword"
#    And I navigate to my account management section
#    Then I should see an option to change my password
#    When I enter my current password "userpassword"
#    And I enter a new password "newuserpassword"
#    And I confirm the new password "newuserpassword"
#    And I save the changes
#    Then I should see a confirmation message "Password changed successfully"
#
#  Scenario: Store Owner updates account details
#    Given I am a Store Owner
#    When I log in with username "storeowner@example.com" and password "storepassword"
#    And I navigate to my account management section
#    Then I should see my account details
#    When I update the business name to "New Sweet Treats"
#    And I update the business address to "789 Chocolate Street"
#    And I save the changes
#    Then I should see a confirmation message "Account details updated successfully"
