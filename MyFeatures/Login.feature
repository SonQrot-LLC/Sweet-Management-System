Feature:Login
  Scenario: the user is already logged in
    Given that the user is logged in
    When The information is  email is "momanani2017@gmail.com" and password is "777"
    Then user failed in log in using another account


  Scenario Outline: Valid information
    Given that the user is not logged in
    When The information is valid email is "<Email>" and password is "<Password>"
    Then User successfully log in

    Examples:
      | Email                      | Password |
      | toostronkm@gmail.com       | 777      |
      | momanani2017@gmail.com     | 777      |
      | n.hamfallah@gmail.com      |  777     |

  Scenario Outline: invalid connection to database
    Given that the user is not logged in
    When The information is valid email is "<Email>" and password is "<Password>" something went wrong with the sql
    Then user failed in log in

    Examples:
      | Email                      | Password |
      | toostronkm@gmail.com       | 777      |
      | momanani2017@gmail.com     | 777      |
      | n.hamfallah@gmail.com      |  777     |
      | mahmood@outlook.com        | 777      |


  Scenario Outline: Invalid email
    Given that the user is not logged in
    When the email is invalid email is "<Email>" and password is "<Password>"
    Then user failed in log in

    Examples:
      | Email                      | Password |
      | tooweakm@gmail.com         | 777      |
      |                            | 777      |
      | momkasat2017@gmail.com     | 777      |

  Scenario Outline: Invalid password
    Given that the user is not logged in
    When the password is invalid email is "<Email>" and password is "<Password>"
    Then user failed in log in

    Examples:
      | Email                      | Password |
      | toostronkm@gmail.com       |          |
      | momanani2017@gmail.com     | wow      |
      | n.hamfallah@gmail.com      | ware     |


  Scenario Outline: Invalid information
    Given that the user is not logged in
    When the information is invalid, email is "<Email>" and password is "<Password>"
    Then user failed in log in
    Examples:
      | Email                      | Password |
      | momkasat2017@gmail.com     | 888      |
      | tooweakm@gmail.com         | soft     |
      |                            |          |
