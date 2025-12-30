package com.studentmanagement.controller;

import com.studentmanagement.entity.Admin;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.AdminRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseService courseService; // <-- Added injection

    // ================= Admin Registration =================
    @PostMapping("/register")
    public String registerAdmin(@RequestBody Admin admin) {
        if (adminRepository.findByUsername(admin.getUsername()).isPresent()) {
            return "EXIST";
        }
        adminRepository.save(admin);
        return "SUCCESS";
    }

    // ================= Admin Login =================
    @PostMapping("/login")
    public boolean loginAdmin(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        return adminOpt.isPresent() && adminOpt.get().getPassword().equals(password);
    }

    // ================= VERIFY STUDENT ID =================
    @GetMapping("/verify-student/{studentId}")
    public ResponseEntity<Boolean> verifyStudent(@PathVariable String studentId) {
        boolean exists = studentRepository.findByStudentId(studentId).isPresent();
        return ResponseEntity.ok(exists);
    }

    // ================= GET STUDENT =================
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable String studentId) {
        return studentRepository.findByStudentId(studentId)
                .map(student -> ResponseEntity.ok(student))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // ================= UPDATE STUDENT (SAFE FIELDS) =================
    @PutMapping("/student/update-basic")
    public String updateStudent(@RequestBody Student req) {

        if (req.getStudentId() == null || req.getStudentId().trim().isEmpty()) {
            return "NOT_FOUND";
        }

        Optional<Student> opt =
                studentRepository.findByStudentId(req.getStudentId());

        if (opt.isEmpty()) {
            return "NOT_FOUND";
        }

        Student s = opt.get();

        // ✅ update only allowed fields
        s.setName(req.getName());
        s.setEmail(req.getEmail());
        s.setPhone(req.getPhone());

        studentRepository.save(s);

        return "UPDATED";
    }

    // ================= DELETE STUDENT =================
    @DeleteMapping("/student/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam String studentId, @RequestParam String name) {
        Optional<Student> opt = studentRepository.findByStudentId(studentId);

        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }

        Student student = opt.get();

        if (!student.getName().equalsIgnoreCase(name)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }

        studentRepository.delete(student);
        return ResponseEntity.ok("DELETED");
    }

    // ================= VIEW ALL STUDENTS =================
    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }
    // ================= Admin Update Semester =================
    @PostMapping("/update-semester")
    public String updateSemester(@RequestBody Map<String, String> payload) {

        String semesterName = payload.get("semesterName");
        int semesterNo;

        try {
            semesterNo = Integer.parseInt(payload.get("semesterNo"));
        } catch (NumberFormatException e) {
            return "Invalid semester number";
        }

        return courseService.adminUpdateSemester(semesterName, semesterNo);
    }
}
