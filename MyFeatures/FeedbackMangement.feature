Feature:Login

  Scenario: creating a valid feedback
    Given that a user is logged in with email "feedbacktest@gmail.com" and password "321"
    When the user create a new feedback with the correct data
    Then the feedback should created successfully


  Scenario: Getting feedback by email
    Given that a user is logged in with email "feedbacktest@gmail.com" and password "321"
    When the user requests feedback by email "feedbacktest@gmail.com"
    Then the feedback associated should be retrieved successfully

  Scenario: Getting feedback by store ID
    Given that a user is logged in with email "feedbacktest@gmail.com" and password "321"
    When the user requests feedback for store ID "1"
    Then the feedback associated should be retrieved successfully

  Scenario: Getting feedback by product ID
    Given that a user is logged in with email "feedbacktest@gmail.com" and password "321"
    When the user requests feedback for product ID "1"
    Then the feedback associated should be retrieved successfully


  Scenario: Successfully deleting order
    Given that a user is logged in with email "feedbacktest@gmail.com" and password "321"
    When The user deletes a feedback with id "1"
    Then the feedback is deleted successfully
