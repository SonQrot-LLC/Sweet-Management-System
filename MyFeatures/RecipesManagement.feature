Feature: Recipe Management

  Scenario: User adds Recipe
    Given that the user is logged in and user is Store Owner or Beneficiary User
    When The user adds recipe with name "Donuts", ingredients "flower, sugar and milk", instruction "cook well" and allergies "cows' milk"
    Then The recipe is added successfully

  Scenario: User searches for an existing Recipe
    Given that the user is logged in and user is Store Owner or Beneficiary User
    When The user searches for product with name "Donuts"
    Then the recipe should appear

  Scenario: User searches for a Recipe that doesn't exist
    Given that the user is logged in and user is Store Owner or Beneficiary User
    When The user searches for product with name "Harese"
    Then A message should appear to notify the user

   Scenario: User filters Recipes based on a food allergy
     Given that the user is logged in and user is Store Owner or Beneficiary User
     When the user filters the results to exclude recipes with "Eggs" food allergy
     Then matching recipes should not be shown

   Scenario: User looks at recipes he shared
     Given that the user is logged in and user is Store Owner or Beneficiary User
     When The user searches for recipes he shared
     Then The recipes should appear

   Scenario: Admin deletes a Recipe
     Given That the admin is logged in
     When The admin Deletes recipe with ID "7"
     Then The recipe will be deleted

   Scenario: Admin updates a Recipe
     Given That the admin is logged in
     When the admin updates recipe with ID "8"
     And sets the name to "Cupcakes"
     And sets the ingredients to "Wheat and Laveva"
     And sets the instructions to "Bake for 45 minutes"
     And sets the allergies to "wheat"
     Then the recipe will be updated


   Scenario: Admin deletes  Recipe that doesn't exist
     Given That the admin is logged in
     When The admin Deletes recipe with ID "2001"
     Then The admin will be notified that product doesn't exist


