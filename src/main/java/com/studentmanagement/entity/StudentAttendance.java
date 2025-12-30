package com.studentmanagement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "student_attendance",
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id","course_code","attendance_date"})
)
public class StudentAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @Column(name="student_id", nullable=false)
    private String studentId;

    @Column(name="semester_name", nullable=false)
    private String semesterName;

    @Column(name="semester_no", nullable=false)
    private Integer semesterNo;

    @Column(name="course_code", nullable=false)
    private String courseCode;

    @Column(name="course_name", nullable=false)
    private String courseName;

    @Column(name="attendance_date", nullable=false)
    private LocalDate attendanceDate;

    @Column(nullable=false)
    private String status; // 'PRESENT' or 'ABSENT'

    // Getters & Setters
    public Long getAttendanceId() { return attendanceId; }
    public void setAttendanceId(Long attendanceId) { this.attendanceId = attendanceId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getSemesterName() { return semesterName; }
    public void setSemesterName(String semesterName) { this.semesterName = semesterName; }

    public Integer getSemesterNo() { return semesterNo; }
    public void setSemesterNo(Integer semesterNo) { this.semesterNo = semesterNo; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public LocalDate getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(LocalDate attendanceDate) { this.attendanceDate = attendanceDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
