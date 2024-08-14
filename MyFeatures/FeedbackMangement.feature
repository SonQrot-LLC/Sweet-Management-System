Feature: Feedback

  Scenario: creating a valid feedback
    Given that a user is logged in with email "feedbacktest@gmail.com" and password "321"
    When the user create a new feedback with the correct data
    Then the feedback should created successfully


  Scenario: Getting feedbacks by email
    Given that a user is logged in with email "feedbacktest@gmail.com" and password "321"
    When the user requests feedback by email "feedbacktest@gmail.com"
    Then the feedback associated should be retrieved successfully

  Scenario: Getting feedbacks by store ID
    Given that a user is logged in with email "feedbacktest@gmail.com" and password "321"
    When the user requests feedback for store ID "1"
    Then the feedback associated should be retrieved successfully

  Scenario: Getting feedbacks by product ID
    Given that a user is logged in with email "feedbacktest@gmail.com" and password "321"
    When the user requests feedback for product ID "1"
    Then the feedback associated should be retrieved successfully

    Scenario:  Getting feedbacks by invalid type
      Given that a user is logged in with email "feedbacktest@gmail.com" and password "321"
      When the user requests feedback for "phone" number "0591234567"
      Then it wont return feedback list and it will fail


  Scenario: Successfully deleting feedback
    Given that a user is logged in with email "feedbacktest@gmail.com" and password "321"
    When The user deletes a feedback with id "1"
    Then the feedback is deleted successfully

  Scenario: Admin trying to get all feedbacks
    Given that the admin is logged in with email "admin@gmail.com" and password "123"
    When The admin tries to get all feedbacks
    Then All feedbacks should be shown

  Scenario: Admin trying to get all feedbacks
    Given that the admin is logged in with email "admin@gmail.com" and password "123"
    When The admin tries to get all feedbacks but there is no connection
    Then All feedbacks should not be shown




