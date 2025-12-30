package com.studentmanagement.repository;

import com.studentmanagement.entity.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Long> {
    boolean existsByStudentIdAndCourseCodeAndAttendanceDate(String studentId, String courseCode, LocalDate attendanceDate);

    // ✅ Add this method to fetch attendance by student, course, and semester
    List<StudentAttendance> findByStudentIdAndCourseCodeAndSemesterNameOrderByAttendanceDateAsc(
            String studentId, String courseCode, String semesterName
    );
}
