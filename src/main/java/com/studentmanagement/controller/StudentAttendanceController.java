package com.studentmanagement.controller;

import com.studentmanagement.entity.StudentAttendance;
import com.studentmanagement.repository.StudentAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin/attendance")
public class StudentAttendanceController {

    @Autowired
    private StudentAttendanceRepository repository;

    @PostMapping("/save")
    public ResponseEntity<?> saveAttendance(@RequestBody List<StudentAttendance> attendanceList) {
        try {
            for (StudentAttendance att : attendanceList) {
                // Check for duplicates
                if (repository.existsByStudentIdAndCourseCodeAndAttendanceDate(
                        att.getStudentId(), att.getCourseCode(), att.getAttendanceDate())) {
                    continue; // Skip duplicate entries
                }
                repository.save(att);
            }
            return ResponseEntity.ok("Attendance saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving attendance: " + e.getMessage());
        }
    }

    // ✅ New endpoint to fetch attendance by student, course, and semester
    @GetMapping("/view")
    public ResponseEntity<List<StudentAttendance>> viewAttendance(
            @RequestParam String studentId,
            @RequestParam String courseCode,
            @RequestParam String semesterName
    ) {
        try {
            List<StudentAttendance> attendanceList = repository
                    .findByStudentIdAndCourseCodeAndSemesterNameOrderByAttendanceDateAsc(
                            studentId, courseCode, semesterName
                    );
            return ResponseEntity.ok(attendanceList);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
