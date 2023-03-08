Feature: Testing different types of products in Best Buy

  Scenario: Check if products list can be accessed by users
    When User sends Get request to list endpoints
    Then User should get the status code of 200

  Scenario: Create a new products and verify if it is added
    When I create a new products with "<name>" "<type>" "<manufacturer>" "<model>" "<upc>" "<image>" "<description>" "<url>"
    Then I verify that products with name is created

  Scenario: check if the name of new product has been updated and verify updated information
    When New product is updated with new "<name>"

  Scenario: check if the product has been deleted by id and verify if deleted
    When I have deleted product by id
    Then I verify that product is deleted
