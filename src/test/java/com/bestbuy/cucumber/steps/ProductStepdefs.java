package com.bestbuy.cucumber.steps;

import com.bestbuy.productinfo.ProductSteps;
import com.bestbuy.serviceinfo.ServiceSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class ProductStepdefs {

    ValidatableResponse response;
    static String name = null;
    static int productsId;
    @Steps
    ProductSteps productSteps;

    @When("^User sends Get request to list endpoints$")
    public void userSendsGetRequestToListEndpoints() {
        response = productSteps.getAllProductsInfo();

    }

    @Then("^User should get the status code of (\\d+)$")
    public void userShouldGetTheStatusCodeOf(int statusCode) {
        response.statusCode(statusCode);
    }

    @When("^I create a new products with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iCreateANewProductsWith(String _name, String type, String manufacturer, String model, String upc, String image, String description, String url) {
        name = TestUtils.getRandomValue() + _name;
        response = productSteps.createProducts(name, type, manufacturer, model, upc, image, description, url);
    }

    @Then("^I verify that products with name is created$")
    public void iVerifyThatProductsWithNameIsCreated() {
        response.statusCode(201);
        HashMap<String, Object> productMap = productSteps.getProductsinfoByName(name);
        Assert.assertThat(productMap, hasValue(name));
    }

    @When("^New product is updated with new \"([^\"]*)\"$")
    public void newProductIsUpdatedWithNew(String _name) {
        name = TestUtils.getRandomValue() + _name;
        response = productSteps.updateProducts(productsId, name);

    }

    @When("^I have deleted product by id$")
    public void iHaveDeletedProductById() {
        response = productSteps.deleteProductsInfoById(productsId);
    }

    @Then("^I verify that product is deleted$")
    public void iVerifyThatProductIsDeleted() {
        response = productSteps.getProductsInfoById(productsId);
    }
}
