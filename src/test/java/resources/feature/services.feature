Feature: Testing different requests on Best Buy services application

  Scenario: Check if services list can be accessed by users
    When User sends Get request to list endpoints
    Then User should get the status code of 200

  Scenario: Create a new services and verify if it is added
    When I create a new services with name "<name>"
    Then I verify that services with name is created

  Scenario: Check if the name has been updated and verify the updated information
    When I update the new service with name "<name>"

  Scenario: Check if the services has been deleted by id and verified if deleted
    When I delete the services by id
    Then I should verify that services is deleted


