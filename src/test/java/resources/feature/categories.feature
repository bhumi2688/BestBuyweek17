Feature: Testing different request on Best Buy Categories Application

  Scenario: Check if categories list can be accessed by users
    When user sends Get request to list endpoints
    Then user should get the status code of 200

  Scenario Outline: Create a new categories and verify if it is added
    When I create a new categories with name "<name>" id "<id>"
    Then I verify that categories with name is created
    Examples:
      | name       | id   |
      | CD Players | abc1 |
      | Minion Cd  | www2 |

  Scenario: Check if the name has been updated and verify the updated information
    When I update the new category with name "<name>"


  Scenario: Check if the categories has been deleted by id and verified if deleted
    When I delete the categories by id
    Then I should verify that categories is deleted

