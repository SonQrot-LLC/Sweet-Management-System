Feature:  Sign up

  Scenario Outline:trying to signing up with an existing email
    Given that the user is not logged in
    When the information exists, the email is "<Email>"
    Then signing up fails

    Examples:
      | Email                      |
      | toostronkm@gmail.com        |
      | momanani2017@gmail.com      |
      | n.hamfallah@gmail.com       |


  Scenario:trying to signing up with incorrect email format
    Given that the user is not logged in
    When the email format is incorrect
    Then signing up fails


  Scenario Outline:trying to signing up with new account
    Given that the user is not logged in
    When the information exists, the email is not "<Email>"
    Then signing up succeeds

    Examples:
      | Email                      |
      | tooweakm@gmail.com         |
      | momanani20011@gmail.com    |
      | momkasat2017@gmail.com     |