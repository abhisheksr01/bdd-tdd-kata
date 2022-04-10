package com.kata.bddtdd.controller;

import com.kata.bddtdd.model.Student;
import com.kata.bddtdd.service.StudentDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentDetailsControllerTest {

    private StudentDetailsController studentDetailsController;
    private StudentDetailsService mockStudentDetailsService;

    @BeforeEach
    void setUp() {
        mockStudentDetailsService = mock(StudentDetailsService.class);
        studentDetailsController = new StudentDetailsController(mockStudentDetailsService);
    }

    @Test
    @DisplayName("Get Student details for a valid name prefix")
    void getStudentDetails_whenNamePrefixIsPassed_shouldReturnStudentDetails() {
        //Arrange
        String namePrefix = "a";
        Student mockData = new Student("abhishek", "rajput");
        when(mockStudentDetailsService.getStudentDetailsMatchedByNamePrefix(namePrefix)).thenReturn(asList(mockData));

        //Action
        List<Student> actualStudentData = studentDetailsController.getStudentDetailsMatchedByNamePrefix(namePrefix);

        //Assertion
        assertAll("actualStudentData",
                () -> assertEquals("abhishek", actualStudentData.get(0).getFirstName()),
                () -> assertEquals("rajput", actualStudentData.get(0).getLastName())
        );
    }

    @Test
    @DisplayName("Get Student details for a valid name prefix")
    void getStudentDetailsByLastName_whenLastNameMatches_shouldReturnStudentDetails() {
        //Arrange
        String lastName = "RaJpuT";
        Student mockData = new Student("abhishek", "rajput");
        when(mockStudentDetailsService.getStudentDetailsByLastName(lastName)).thenReturn(asList(mockData));

        //Action
        List<Student> actualStudentData = studentDetailsController.getStudentDetailsByLastName(lastName);

        //Assertion
        assertAll("actualStudentData",
                () -> assertEquals("abhishek", actualStudentData.get(0).getFirstName()),
                () -> assertEquals("rajput", actualStudentData.get(0).getLastName())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"11111111", "@bhishek"})
    void getStudentDetailsByLastName_whenNonAlphabeticNamePrefixIsPassed_shouldThrowBadRequestError(String namePrefix) {
        // This is another way of testing the exception scenarios
        String actualErrorMessage = null;
        final String expectedErrorMessage = "400 bad request a name prefix can only contain alphabets";
        try {
            studentDetailsController.getStudentDetailsMatchedByNamePrefix(namePrefix);
        } catch (Exception exception) {
            actualErrorMessage = exception.getMessage();
        }
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}