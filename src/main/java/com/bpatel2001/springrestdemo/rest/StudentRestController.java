package com.bpatel2001.springrestdemo.rest;

import com.bpatel2001.springrestdemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // Define @PostConstruct to load the student data

    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();

        // This will convert from POJO to JSON
        theStudents.add(new Student("Bhavik", "Patel"));
        theStudents.add(new Student("John", "Doe"));
        theStudents.add(new Student("Hank", "Hill"));

    }

    // Define endpoint for /students

    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudents;
    }

    // Define endpoint for /students/{studentId}

    @GetMapping("/students/{studentId}")
    // By default, the variables should match between the name of the mapping you are trying to do and the object passed in: {studentId} -> int studentId
    public Student getStudent(@PathVariable int studentId) {
        // Index from the list

        // Check the studentID against list size - throw StudentNotFoundException if not found
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException(("Student id not found - " + studentId));
        }
        return theStudents.get(studentId);
    }

    // Add an exception handler using @ExceptionHandler

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

        // Create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // Return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
