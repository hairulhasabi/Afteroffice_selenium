Feature: Employee API


  Scenario: Register a new employee
    When Send employee to register with body:
      """
      {
        "email": "test113@test.com",
        "password": "test",
        "full_name": "test name",
        "department": "IT",
        "title": "QA"
      }
      """
    Then The response status must be 200
    And The response schema should be match with schema "register-schema.json"

  Scenario: Login with registered employee
    When Send employee to login with body:
      """
      {
        "email": "test113@test.com",
        "password": "test"
      }
      """
    Then The response status must be 200
    And Save the token from the response to local storage

  Scenario:
    Given Make sure token in local storage not empty
    When Send a request to update with body:
      """
      {
        "email": "test113@test.com",
        "password": "test",
        "full_name": "Ini nama yg udh diupdate ya",
        "department": "Tech",
        "title": "Backend Engineer"
      }
      """
    Then The response status must be 200
    And Full name in the response must be "Ini nama yg udh diupdate ya"
    And Department in the response must be "Tech"
    And Title in the response must be "Backend Engineer"

  Scenario:
    Given Make sure token in local storage not empty
    When Send a request to get employee
    Then The response status must be 200
    And Full name in the response must be "Ini nama yg udh diupdate ya"
    And Department in the response must be "Tech"
    And Title in the response must be "Backend Engineer"

  Scenario:
    Given Make sure token in local storage not empty
    When Send a request to delete employee
    Then The response status must be 200