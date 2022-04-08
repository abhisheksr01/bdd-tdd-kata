package com.kata.bddtdd.service;

import com.kata.bddtdd.model.Student;
import com.kata.bddtdd.repository.StudentDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentDetailsService {

    private StudentDetailsRepository studentDetailsRepository;

    public StudentDetailsService(StudentDetailsRepository studentDetailsRepository) {
        this.studentDetailsRepository = studentDetailsRepository;
    }

    public List<Student> getStudentDetailsMatchedByNamePrefix(String namePrefix) {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student("abhishek", "rajput");
        Student student1 = new Student("novita", "s");
        Student student2 = new Student("shin", "chan");
        Student student3 = new Student("ash", "ketchum");
        Student student4 = new Student("delia", "ketchum");
        Student student5 = new Student("john", "cena");
        Student student6 = new Student("roger", "federer");
        Student student7 = new Student("david", "beckham");
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.add(student6);
        studentList.add(student7);

        return studentList.stream().filter(
                studentDetails -> studentDetails.getFirstName().startsWith(namePrefix)
        ).collect(Collectors.toList());
    }
}
