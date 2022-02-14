@login
Feature: User should be able to login

  Scenario: Login as a User
    Given the user is on the login page
    When the user enters the username password
    Then the user should be able to login


