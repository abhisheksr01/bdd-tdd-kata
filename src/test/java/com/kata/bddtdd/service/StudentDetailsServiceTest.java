package com.kata.bddtdd.service;

import com.kata.bddtdd.model.Student;
import com.kata.bddtdd.repository.StudentDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentDetailsServiceTest {

    private StudentDetailsService studentDetailsService;
    private StudentDetailsRepository mockStudentDetailsRepository;

    @BeforeEach
    void setUp() {
        mockStudentDetailsRepository = mock(StudentDetailsRepository.class);
        studentDetailsService = new StudentDetailsService(mockStudentDetailsRepository);
    }

    @ParameterizedTest
    @CsvSource({"a,abhishek,rajput", "A,abhishek,rajput", "n,novita,s"})
    void getStudentDetailsFromDB_whenNamePrefixWithaIsPassed_shouldReturnStudentDetailsWithFirstNameStartingWithA(String namePrefix, String firstName, String lastName) {
        when(mockStudentDetailsRepository.getStudentDetails()).thenReturn(getStudentsDetails());

        List<Student> actualStudentData = studentDetailsService.getStudentDetailsMatchedByNamePrefix(namePrefix);

        assertAll("actualStudentData",
                () -> assertEquals(firstName, actualStudentData.get(0).getFirstName()),
                () -> assertEquals(lastName, actualStudentData.get(0).getLastName())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"RaJPut", "rajput"})
    void getStudentDetailsByLastName_whenRaJPutAsLastNameIsPassed_shouldReturnStudentDetailsWithLastNameWithRaJPut_AndIsCaseInsensitive(String lastName) {
        when(mockStudentDetailsRepository.getStudentDetails()).thenReturn(getStudentsDetails());

        List<Student> actualStudentData = studentDetailsService.getStudentDetailsByLastName(lastName);

        assertAll("actualStudentData",
                () -> assertEquals("abhishek", actualStudentData.get(0).getFirstName()),
                () -> assertTrue("rajput".equalsIgnoreCase(actualStudentData.get(0).getLastName())
                ));
    }

//    TODO: This test should be refactored to assert against more specific scenarios
    @Test
    void getStudentDetailsFromDB_whenNoStudentDetailsIsFound_shouldThrowNotFoundException() {
        String namePrefix = "Z";

        assertThrows(HttpClientErrorException.class, () -> {
            studentDetailsService.getStudentDetailsMatchedByNamePrefix(namePrefix);
        });
    }

    private List<Student> getStudentsDetails() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student("abhishek", "rajput");
        Student student1 = new Student("novita", "s");
        Student student2 = new Student("shin", "chan");
        Student student3 = new Student("ash", "ketchum");
        Student student4 = new Student("delia", "ketchum");
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        return studentList;
    }

}