package com.studentmanagement.controller;

import com.studentmanagement.entity.Course;
import com.studentmanagement.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class CourseAdminController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/course/add")
    public String addCourse(@RequestBody Course course) {

        // -------- BASIC VALIDATION --------
        if (course.getDepartment() == null ||
                course.getCourseName() == null ||
                course.getDepartment().isBlank() ||
                course.getCourseName().isBlank()) {

            return "INVALID_DATA";
        }

        // -------- DUPLICATE CHECK --------
        Optional<Course> existing =
                courseRepository.findByDepartmentAndCourseName(
                        course.getDepartment(),
                        course.getCourseName()
                );

        if (existing.isPresent()) {
            return "COURSE_ALREADY_ADDED";
        }

        // -------- SAVE COURSE --------
        courseRepository.save(course);
        return "COURSE_ADDED";
    }
}
