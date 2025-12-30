package com.studentmanagement.controller;

import com.studentmanagement.entity.Course;
import com.studentmanagement.entity.StudentCourse;
import com.studentmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/student/course")
public class StudentCourseController {

    @Autowired
    private CourseService courseService;

    // ==============================
    // FETCH AVAILABLE COURSES
    // ==============================
    @GetMapping("/available")
    public List<Course> getAvailableCourses(
            @RequestParam String studentId,
            @RequestParam String department,
            @RequestParam int semesterNo // Accept input from frontend
    ) {
        // Fetch semester info from semester_update table
        Map<String, Object> semesterInfo = courseService.getSemesterInfoByStudent(studentId);
        String semesterName = (String) semesterInfo.get("semesterName");

        // Fetch only courses not yet registered
        return courseService.getAvailableCourses(studentId, department, semesterName, semesterNo);
    }

    // ==============================
    // REGISTER COURSES
    // ==============================
    @PostMapping("/register")
    public String registerCourses(@RequestBody Map<String, Object> payload) {

        String studentId = (String) payload.get("studentId");
        String department = (String) payload.get("department");
        String semesterName = (String) payload.get("semesterName");
        int semesterNo = ((Number) payload.get("semesterNo")).intValue(); // Safer casting

        // List of course objects with courseCode, section, subSection
        List<Map<String, String>> courses =
                (List<Map<String, String>>) payload.get("courses");

        courseService.registerCourses(
                studentId,
                department,
                semesterName,
                semesterNo,
                courses
        );

        return "SUCCESS";
    }

    // ==============================
    // FETCH REGISTERED COURSES
    // ==============================
    @GetMapping("/student-courses")
    public List<StudentCourse> getStudentCourses(
            @RequestParam String studentId,
            @RequestParam String semesterName,
            @RequestParam(required = false) Integer semesterNo
    ) {
        return courseService.getRegisteredCourses(studentId, semesterName);
    }
}
