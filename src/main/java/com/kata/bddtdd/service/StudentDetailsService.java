package com.kata.bddtdd.service;

import com.kata.bddtdd.model.Student;
import com.kata.bddtdd.repository.StudentDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentDetailsService {

    private StudentDetailsRepository studentDetailsRepository;

    public StudentDetailsService(StudentDetailsRepository studentDetailsRepository) {
        this.studentDetailsRepository = studentDetailsRepository;
    }

    public List<Student> getStudentDetailsMatchedByNamePrefix(String namePrefix) {
        List<Student> studentList = this.studentDetailsRepository.getStudentDetails();

        return studentList.stream().filter(
                studentDetails -> studentDetails.getFirstName().toLowerCase().startsWith(namePrefix.toLowerCase())
        ).collect(Collectors.toList());
    }

    public List<Student> getStudentDetailsByLastName(String lastName) {
        List<Student> studentList = this.studentDetailsRepository.getStudentDetails();
        return studentList.stream().filter(
                studentDetails -> studentDetails.getLastName().equalsIgnoreCase(lastName)
        ).collect(Collectors.toList());
    }
}
