package com.studentmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_results")
public class StudentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "semester_name")
    private String semesterName;

    @Column(name = "semester_no")
    private int semesterNo;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "credit_hours")
    private Double creditHours;

    @Column(name = "grade")
    private String grade;

    @Column(name = "point")
    private Double point;

    @Column(name = "grade_point")
    private Double gradePoint; // Add this field in entity

    // Getters & Setters
    public Long getId() { return id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getSemesterName() { return semesterName; }
    public void setSemesterName(String semesterName) { this.semesterName = semesterName; }

    public int getSemesterNo() { return semesterNo; }
    public void setSemesterNo(int semesterNo) { this.semesterNo = semesterNo; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Double getCreditHours() { return creditHours; }
    public void setCreditHours(Double creditHours) { this.creditHours = creditHours; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public Double getPoint() { return point; }
    public void setPoint(Double point) { this.point = point; }

    public Double getGradePoint() { return gradePoint; }
    public void setGradePoint(Double gradePoint) { this.gradePoint = gradePoint; } // Add setter
}
