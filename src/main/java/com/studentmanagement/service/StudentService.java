package com.studentmanagement.service;

import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    // ================= REGISTER =================
    public String registerStudent(Student student) {

        Optional<Student> byId = repo.findByStudentId(student.getStudentId());
        Optional<Student> byName = repo.findByName(student.getName());

        if (byId.isPresent() || byName.isPresent()) {
            return "EXIST";
        }

        repo.save(student);
        return "SUCCESS";
    }

    // ================= LOGIN =================
    public boolean loginStudent(String studentId, String password) {
        if(studentId == null || password == null) return false; // prevent null
        return repo.findByStudentIdAndPassword(studentId, password).isPresent();
    }

    // ================= DASHBOARD =================
    public Student getStudentById(String studentId) {
        if(studentId == null) return null;
        return repo.findByStudentId(studentId).orElse(null);
    }
}
