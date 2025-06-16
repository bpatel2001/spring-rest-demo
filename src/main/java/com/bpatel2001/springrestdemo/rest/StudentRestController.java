package com.bpatel2001.springrestdemo.rest;

import com.bpatel2001.springrestdemo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // Define endpoint for /students

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> theStudents = new ArrayList<>();

        // This will convert from POJO to JSON
        theStudents.add(new Student("Bhavik", "Patel"));
        theStudents.add(new Student("John", "Doe"));
        theStudents.add(new Student("Hank", "Hill"));

        return theStudents;
    }
}
