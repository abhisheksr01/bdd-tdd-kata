package com.kata.bddtdd.repository;

import com.kata.bddtdd.model.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentDetailsRepositoryTest {

    @Test
    void getStudentDetails_whenCalled() {
        List<Student> expectedStudentDetails = new ArrayList<>();
        Student student = new Student("abhishek", "rajput");
        Student student1 = new Student("novita", "s");
        Student student2 = new Student("shin", "chan");
        Student student3 = new Student("ash", "ketchum");
        Student student4 = new Student("delia", "ketchum");
        Student student5 = new Student("john", "cena");
        Student student6 = new Student("roger", "federer");
        Student student7 = new Student("david", "beckham");
        expectedStudentDetails.add(student);
        expectedStudentDetails.add(student1);
        expectedStudentDetails.add(student2);
        expectedStudentDetails.add(student3);
        expectedStudentDetails.add(student4);
        expectedStudentDetails.add(student5);
        expectedStudentDetails.add(student6);
        expectedStudentDetails.add(student7);
        StudentDetailsRepository studentDetailsRepository = new StudentDetailsRepository();

        List<Student> actualStudentDetails = studentDetailsRepository.getStudentDetails();

        assertTrue(expectedStudentDetails.equals(actualStudentDetails));
    }
}