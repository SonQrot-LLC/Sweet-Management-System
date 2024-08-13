Feature:  Sign up

  Scenario Outline:trying to signing up with an existing email
    Given that the user is not logged in
    When the information exists, the email is "<Email>"
    Then signing up fails

    Examples:
      | Email                       |
      | toostronkm@gmail.com        |
      | momanani2017@gmail.com      |
      | n.hamfallah@gmail.com       |


  Scenario Outline: Trying to sign up with incorrect email format
    Given that the user is not logged in
    When the email "<email>" format is incorrect
    Then signing up fails
    When the email is null
    Then signing up fails

    Examples:
      | email                |
      | invalid-email        |
      | another.invalid@     |
      | no-at-sign.com       |
      |                      |
      | missing-domain@.com  |
      | missing-part@domain  |


  Scenario Outline:trying to signing up with new account fails
    Given that the user is not logged in
    When the email format is correct and the email "<Email>" does not exist in the database but there is a problem in dataBase connection
    Then signing up fails

    Examples:
      | Email                      |
      | tooweankm@gmail.com        |
      | momanani20011@gmail.com    |
      | momkaat2017@gmail.com      |


  Scenario Outline:trying to signing up with new account
    Given that the user is not logged in
    When the email format is correct and the email "<Email>" does not exist in the database
    Then signing up succeeds


    Examples:
      | Email                      |
      | tooweankm@gmail.com        |
      | momanani20011@gmail.com    |
      | momkaat2017@gmail.com      |



  Scenario: trying to signing up with new account but i am logged in
    Given that the user is logged in
    When the email format is correct and the email "test@gmail.com" does not exist in the database but the user is logged in
    Then signing up with a new account fails

