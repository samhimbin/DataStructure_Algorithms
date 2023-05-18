
Feature: loginpage using Datatable

  @tag1
  Scenario: validate loginpage using datatable
    Given user is in homepage of dsalgo website
    When usr clicks on the login link to give username and password
    Then user sign up with following details
       | nitha | nithasdet109 |
      