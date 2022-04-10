package com.kata.bddtdd.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinition {

    String requestParameter = null;
    RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<List> response;

    @Given("Student enters name prefix {string}")
    public void student_enters_the_roll_number(String namePrefix) {
        this.requestParameter = namePrefix;
    }

    @When("The student makes a call to {string} get the details")
    public void the_student_makes_a_call_to_get_the_details(String url) {
        response = restTemplate.getForEntity(url + this.requestParameter, List.class);
    }

    @Then("The API should return the student details and response code {int}")
    public void the_api_should_return_the_student_details_and_name_as(int statusCode) {
        assertEquals(statusCode, response.getStatusCodeValue());
    }

    // Second scenario where we are reusing when step definition from 1st scenario.
    @Given("Student enters last name {string}")
    public void student_enters_last_name(String lastName) {
        // Write code here that turns the phrase above into concrete actions
        this.requestParameter = lastName;
    }

    @Then("The API should return the student details where firstname is {string}, lastname is {string}")
    public void the_api_should_return_the_student_details_where_firstname_is_lastname_is(String expectedFirstName, String expectedLastName) {
        LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) ((ArrayList) response.getBody()).get(0);
        assertEquals(expectedFirstName, linkedHashMap.get("firstName"));
        assertEquals(expectedLastName, linkedHashMap.get("lastName"));
    }

    @Then("response code {int}")
    public void response_code(Integer expectedStatusCode) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(expectedStatusCode, response.getStatusCodeValue());
    }
}
