package com.studentmanagement.repository;

import com.studentmanagement.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    // Find all courses registered by a specific student
    List<StudentCourse> findByStudentId(String studentId);

    // ✅ New method: find by studentId + semesterName
    List<StudentCourse> findByStudentIdAndSemesterName(String studentId, String semesterName);

    // Check if a student has already registered a specific course
    boolean existsByStudentIdAndCourseCode(String studentId, String courseCode);

    // Get all course codes registered by a student (for filtering)
    @Query("SELECT sc.courseCode FROM StudentCourse sc WHERE sc.studentId = :studentId")
    List<String> findCourseCodesByStudentId(@Param("studentId") String studentId);

    // =========================
    @Modifying
    @Transactional
    @Query("""
        UPDATE StudentCourse sc
        SET sc.semesterName = :semesterName,
            sc.semesterNo = :semesterNo
        WHERE sc.studentId = :studentId
    """)
    int updateSemesterByStudentId(
            @Param("studentId") String studentId,
            @Param("semesterName") String semesterName,
            @Param("semesterNo") int semesterNo
    );
}
