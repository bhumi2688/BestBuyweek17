package com.bestbuy.cucumber.steps;

import com.bestbuy.categoriesinfo.CategoriesSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class CategoriesStepDefs {
    ValidatableResponse response;
    static String name = null;
    static String id = null;
    static Object categoriesId;

    @Steps
    CategoriesSteps categoriesSteps;

    @When("^user sends Get request to list endpoints$")
    public void userSendsGetRequestToListEndpoints() {
        response = categoriesSteps.getAllCategoriesInfo();

    }

    @Then("^user should get the status code of (\\d+)$")
    public void userShouldGetTheStatusCodeOf(int statusCode) {
        response.statusCode(statusCode);
    }


    @When("^I create a new categories with name \"([^\"]*)\" id \"([^\"]*)\"$")
    public void iCreateANewCategoriesWithNameId(String _name, String _id) {
        name = TestUtils.getRandomValue() + _name;
        id = TestUtils.getRandomValue() + _id;
        response = categoriesSteps.createCategories(name, id);
    }

    @Then("^I verify that categories with name is created$")
    public void iVerifyThatCategoriesWithNameIsCreated() {
        response.statusCode(201);
        HashMap<String, Object> map = categoriesSteps.getcategoriesinfobyname(name);
        Assert.assertThat(map, hasValue(name));

    }

    @When("^I update the new category with name \"([^\"]*)\"$")
    public void iUpdateTheNewCategoryWithName(String _name) {
        name = TestUtils.getRandomValue() + _name;
        response = categoriesSteps.updateCategories(name, id);
    }

    @When("^I delete the categories by id$")
    public void iDeleteTheCategoriesById() {
        response = categoriesSteps.deleteCategoriesInfobyId(id);
    }

    @Then("^I should verify that categories is deleted$")
    public void iShouldVerifyThatCategoriesIsDeleted() {
        response = categoriesSteps.getcategogiesinfobyid(id);
    }
}

