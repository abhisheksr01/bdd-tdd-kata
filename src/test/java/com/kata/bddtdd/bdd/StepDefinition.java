package com.kata.bddtdd.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinition {

    private String requestParameter = null;
    private String exceptionMessage;
    private int exceptionStatusCode;
    private ResponseEntity response;
    private RestTemplate restTemplate = new RestTemplate();

    @Given("Student enters name prefix {string}")
    public void student_enters_the_roll_number(String namePrefix) {
        this.requestParameter = namePrefix;
    }

    @When("The student makes a call to {string} and get the details")
    public void the_student_makes_a_call_to_and_get_the_details(String url) {
        System.out.println("bbbbbbbbbbbbb: " + this.requestParameter);
        try {
            response = restTemplate.getForEntity(url + this.requestParameter, List.class);
        } catch (HttpClientErrorException exception) {
            exceptionStatusCode = exception.getRawStatusCode();
            exceptionMessage = exception.getResponseBodyAsString();
        }

    }

    @Then("The API should return the student details and response code {int}")
    public void the_api_should_return_the_student_details_and_name_as(int statusCode) {
        assertEquals(statusCode, response.getStatusCodeValue());
    }

    /**
     * Kata: 1.4: Testing for retrieving student details by last name
     * Reusing When step from previous steps.
     */
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

    /**
     * Kata: 1.5: Testing for no matching student details found scenario.
     * Reusing Given and When (although we have to add try catch block to handle exception scenarios) steps from previous steps.
     */
    @Then("The API should return a message {string} and response code {int}")
    public void the_api_should_return_a_message_and_response_code(String expectedErrorMessage, Integer expectedStatusCode) {
        assertEquals(expectedStatusCode, exceptionStatusCode);
        assertEquals(expectedErrorMessage, exceptionMessage);
    }

    /**
     * Kata: 1.6: Testing for invalid name prefix.
     * Here we are using Scenario Outline concept of Cucumber to avoid duplication of scenario in feature file
     * Reusing When and Then steps from previous steps.
     */
    @Given("Student enters invalid name prefix {string}")
    public void student_enters_invalid_name_prefix(String namePrefix) {
        this.requestParameter = namePrefix;
    }

}
