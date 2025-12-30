package com.studentmanagement.repository;

import com.studentmanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // Find a course by department and course name (used for uniqueness check)
    Optional<Course> findByDepartmentAndCourseName(String department, String courseName);

    // Find all courses for a department and semester number
    List<Course> findByDepartmentAndSemesterNo(String department, int semesterNo);

    // ✅ NEW: Find all courses matching a list of course codes
    List<Course> findByCourseCodeIn(List<String> courseCodes);
}
