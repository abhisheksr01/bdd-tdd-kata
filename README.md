# Introduction

## BDD and TDD
BDD stands for Behaviour Driven Development, It's an agile software development process that encourages collaboration among developers, quality assurance testers, and customer representatives in a software project.

You can refer below links to learn more about BDD:
- https://www.merixstudio.com/blog/behavior-driven-development-python
- https://cucumber.io/blog/2015/12/08/example-mapping-introduction
- https://www.youtube.com/watch?v=VwvrGfWmG_U
- https://github.com/behave/behave
- https://www.amazon.co.uk/Specification-Example-Successful-Deliver-Software/dp/1617290084
- http://bddbooks.com/
- https://pragprog.com/book/hwcuc/the-cucumber-book
- https://inviqa.com/blog/bdd-guide
- https://leanpub.com/50quickideas-tests
- https://testing.googleblog.com/2015/04/just-say-no-to-more-end-to-end-tests.html

TDD stands for Test Driven Development, It's a software development process relying on software requirements being converted to test cases before software is fully developed, and tracking all software development by repeatedly testing the software against all test cases.

You can refer below links to learn more about BDD:
- https://www.infoq.com/news/2009/03/TDD-Improves-Quality
- https://medium.com/@carlosalmonte04/tdd-why-use-it-7747e6e091c4
- https://ieeexplore.ieee.org/abstract/document/1251029/
- https://www.upwork.com/hiring/for-clients/test-driven-development/
- https://dl.acm.org/citation.cfm?id=2915996
- https://cloudnative.ly/which-order-to-write-your-tests-7ea2937761a1

## Application Requirement

Currently, we need to expose a GET endpoint that allows the client to search for students whose nameprefix matches the requested string.

Example request and response:
```aidl
Request:
http://localhost:8080/search/a

Response:
[{"firstName":"abhishek","lastName":"rajput"},{"firstName":"ash","lastName":"ketchum"}]
```

## Applying BDD in Spring Boot for automation

We are following BDD first approach where we wrote a failing feature/acceptance criteria thus driving our development through behavior and then followed by Test Driven Development.
A feature is not considered as developed until all the Unit Tests (TDD) and feature (BDD) passes.

### Adding required dependencies
We are using Cucumber framework for implementing BDD in this boot.

Please add below dependencies in your pom.xml
```xml
        <dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-core</artifactId>
			<version>6.8.0</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-java</artifactId>
			<version>6.8.0</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-junit</artifactId>
			<version>6.8.0</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-spring</artifactId>
			<version>6.8.0</version>
			<scope>test</scope>
		</dependency>

```

Run mvnw install command to download all dependencies.
```bash
./mvnw install
```

### Add features and Spring-Cucumber Integration
- Create a resources subdirectory inside the test directory as `src/test/resources` and add a feature file called student.feature as below:

```manifest
Feature: Retrieve Student Information
  Scenario: When a name prefix is passed all the students information starting with that is returned
    Given Student enters name prefix "n"
    When The student makes a call to get the details
    Then The API should return the student details and response code 200
```

- Create a CucumberIntegration.java (you may have any relevant name) class in the test directory as below:
```java
@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(
        classes = BDDTDDKataApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberOptions(features = "src/test/resources", plugin = {"pretty",
        "html:build/reports/cucumber/cucumber-report.html"})
public class CucumberIntegration {
}
```

- Create another class called StepDefinition.java (you may have any relevant name) and add below step definitions which are code implementation of the feature that we have added.
```java
package com.kata.bddtdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {

    @Given("Student enters name prefix {string}")
    public void student_enters_name_prefix(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("The student makes a call to get the details")
    public void the_student_makes_a_call_to_get_the_details() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("The API should return the student details and response code {int}")
    public void the_api_should_return_the_student_details_and_response_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}

```

From your IDE run the Class CucumberIntegration and the test will fail with PendingException.

### Implement the BDD test requirements in the Step Definition

Our BDD test requirement is to call an GET API to retrieve student details with a given name prefix and assert that.

```java
package com.kata.bddtdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinition {

    String namePrefix = null;
    private ResponseEntity response;
    RestTemplate restTemplate = new RestTemplate();

    @Given("Student enters name prefix {string}")
    public void student_enters_the_roll_number(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @When("The student makes a call to get the details")
    public void the_student_makes_a_call_to_get_the_details() {
        response = restTemplate.getForEntity("http://localhost:8080/search/" + this.namePrefix, List.class);
    }

    @Then("The API should return the student details and response code {int}")
    public void the_api_should_return_the_student_details_and_name_as(int statusCode) {
        assertEquals(statusCode, response.getStatusCodeValue());
    }
}

```

From your IDE run the Class CucumberIntegration and the test will fail with 404 as currently no endpoint as above exists.

## Building a GET API

Now you should follow the TDD approach and build your GET API. The development is not completed until all the Unit and Cucumber Tests are passing.

You can refer the current code base to see how it's been currently implemented.
