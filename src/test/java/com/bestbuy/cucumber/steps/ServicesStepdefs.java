package com.bestbuy.cucumber.steps;

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

public class ServicesStepdefs {
    ValidatableResponse response;
    static String name = null;
    static int servicesId;
    @Steps
    ServiceSteps serviceSteps;

    @When("^User sends Get request to list endpoints$")
    public void userSendsGetRequestToListEndpoints() {
        response = serviceSteps.getAllServicesInfo();

    }

    @Then("^User should get the status code of (\\d+)$")
    public void userShouldGetTheStatusCodeOf(int statusCode) {
        response.statusCode(200);
    }

    @When("^I create a new services with name \"([^\"]*)\"$")
    public void iCreateANewServicesWithName(String _name) throws Throwable {
        name = TestUtils.getRandomValue() + _name;
        response = serviceSteps.createServices(name);

    }

    @Then("^I verify that services with name is created$")
    public void iVerifyThatServicesWithNameIsCreated() {
        response.statusCode(201);
        HashMap<String, Object> serviceMap = serviceSteps.getservicesinfobyname(name);
        Assert.assertThat(serviceMap, hasValue(name));
    }


    @When("^I update the new service with name \"([^\"]*)\"$")
    public void iUpdateTheNewServiceWithName(String _name) {
        name = TestUtils.getRandomValue() + _name;
        response = serviceSteps.updateServices(name, servicesId);
    }

    @When("^I delete the services by id$")
    public void iDeleteTheServicesById() {
        response = serviceSteps.deleteServicessInfobyId(servicesId);
    }

    @Then("^I should verify that services is deleted$")
    public void iShouldVerifyThatServicesIsDeleted() {
        response = serviceSteps.getservicesinfobyid(servicesId);
    }
}
