package com.studentmanagement.controller;

import com.studentmanagement.entity.Student;
import com.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ================= REGISTER =================
    @PostMapping("/register")
    public String registerStudent(@RequestBody Student student) {
        return studentService.registerStudent(student);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public boolean loginStudent(@RequestBody Student student) {
        return studentService.loginStudent(
                student.getStudentId(),
                student.getPassword()
        );
    }

    // ================= DASHBOARD PROFILE =================
    @GetMapping("/profile/{studentId}")
    public Student getStudentProfile(@PathVariable String studentId) {
        return studentService.getStudentById(studentId);
    }
}
