package com.kata.bddtdd.service;

import com.kata.bddtdd.Student;
import com.kata.bddtdd.repository.StudentDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void getStudentDetailsFromDB_whenNamePrefixWithAIsPassed_shouldReturnStudentDetailsWithFirstNameStartingWithA() {
        String namePrefix = "a";
        when(mockStudentDetailsRepository.getStudentDetails()).thenReturn(getStudentsDetails());

        List<Student> actualStudentData = studentDetailsService.getStudentDetailsMatchedByNamePrefix(namePrefix);

        assertAll("actualStudentData",
                () -> assertEquals("abhishek", actualStudentData.get(0).getFirstName()),
                () -> assertEquals("rajput", actualStudentData.get(0).getLastName())
        );
    }


    @Test
    void getStudentDetailsFromDB_whenNamePrefixWithNIsPassed_shouldReturnStudentDetailsWithFirstNameStartingWithN() {
        String namePrefix = "n";
        when(mockStudentDetailsRepository.getStudentDetails()).thenReturn(getStudentsDetails());

        List<Student> actualStudentData = studentDetailsService.getStudentDetailsMatchedByNamePrefix(namePrefix);

        assertAll("actualStudentData",
                () -> assertEquals("novita", actualStudentData.get(0).getFirstName())
        );
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