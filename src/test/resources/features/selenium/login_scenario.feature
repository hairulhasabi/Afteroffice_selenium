Feature: Login Scenario

Background: User landend to login page
    Given User landing to logged ecommerce

Scenario: User input valid credentials
    When User input email "simanjuntakalbert57@gmail.com" and password "XBf@rWNvByn!#K8"
    Then User redirect to homepage

Scenario Outline: User input invalid email
    When User input email "<email>" and password "<password>"
    Then Verify error message "<email_error_message>" on email
    And Verify error message "<password_error_message>" on password

    Examples:
    |email                          | password        | email_error_message | password_error_message |
    |simanjuntakalbert57@gmail.com  |                 |                     | *Password is required  |
    |simanjuntakalbert57            | XBf@rWNvByn!#K8 | *Enter Valid Email  |                        |
    |                               | XBf@rWNvByn!#K8 | *Email is required  |                        |
    |                               |                 | *Email is required  | *Password is required  |