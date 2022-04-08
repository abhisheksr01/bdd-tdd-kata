package com.kata.bddtdd.repository;

import com.kata.bddtdd.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDetailsRepository {

    public List<Student> getStudentDetails() {
        List<Student> studentDetailsList = new ArrayList<>();
        Student student = new Student("abhishek", "rajput");
        Student student1 = new Student("novita", "s");
        Student student2 = new Student("shin", "chan");
        Student student3 = new Student("ash", "ketchum");
        Student student4 = new Student("delia", "ketchum");
        Student student5 = new Student("john", "cena");
        Student student6 = new Student("roger", "federer");
        Student student7 = new Student("david", "beckham");
        studentDetailsList.add(student);
        studentDetailsList.add(student1);
        studentDetailsList.add(student2);
        studentDetailsList.add(student3);
        studentDetailsList.add(student4);
        studentDetailsList.add(student5);
        studentDetailsList.add(student6);
        studentDetailsList.add(student7);
        return studentDetailsList;
    }
}
