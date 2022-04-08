package com.kata.bddtdd.controller;

import com.kata.bddtdd.Student;
import com.kata.bddtdd.service.StudentDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentDetailsController {

    private StudentDetailsService studentDetailsService;

    public StudentDetailsController(StudentDetailsService studentDetailsService) {
        this.studentDetailsService = studentDetailsService;
    }

    @GetMapping("/search/{namePrefix}")
    public List<Student> getStudentDetails(@PathVariable String namePrefix) {
        return this.studentDetailsService.getStudentDetailsMatchedByNamePrefix(namePrefix);
    }
}
