Feature: AccountManagement


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

  Scenario:  User makes invalid account updates
    Given I log in with username "ahmad123@gmail.com" and password "777"
    When I chose an invalid account updateType
    Then Update fails

  Scenario:  User makes invalid account updates
    Given I log in with username "ahmad123@gmail.com" and password "777"
    When I give valid updates and something went wrong with sql
    Then Update fails

  Scenario: User deletes account
    Given I log in with username "ahmad123@gmail.com" and password "777"
    When I delete account
    Then Account is deleted

  Scenario: Admin changing the roles of an account
    Given I log in with username "mahmood@gmail.com" and password "777" and i am an Admin
    When I update someone's role to  "admin"
    Then The role is Updated

  Scenario: Admin trying to update nonexistent account
    Given I log in with username "mahmood@gmail.com" and password "777" and i am an Admin
    When I update nonexistent user in the DB role to  "admin"
    Then Update fails

  Scenario: None admin trying to update another account
    Given I log in with username "ahmad123@gmail.com" and password "777" and i am not an Admin
    When I update "momanani2017@gmail.com" password to "888"
    Then Update fails




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




#  Scenario: Store Owner updates account details
#    Given I am a Store Owner
#    When I log in with username "storeowner@example.com" and password "storepassword"
#    And I navigate to my account management section
#    Then I should see my account details
#    When I update the business name to "New Sweet Treats"
#    And I update the business address to "789 Chocolate Street"
#    And I save the changes
#    Then I should see a confirmation message "Account details updated successfully"
