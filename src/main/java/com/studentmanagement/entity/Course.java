package com.studentmanagement.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(
        name = "course",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"department", "course_name"})
        }
)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String department;

    @JsonProperty("semesterName")
    @Column(name = "semester_name")
    private String semesterName;

    @JsonProperty("semesterNo")
    @Column(name = "semester_no")
    private int semesterNo;

    @JsonProperty("courseName")
    @Column(name = "course_name")
    private String courseName;

    @JsonProperty("courseCode")
    @Column(name = "course_code")
    private String courseCode;

    // ✅ REQUIRED UPDATE (DO NOT REMOVE)
    @JsonProperty("creditHours")
    @Column(name = "credit_hours")
    private Double creditHours;

    // Getters and Setters
    public Long getId() { return id; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getSemesterName() { return semesterName; }
    public void setSemesterName(String semesterName) { this.semesterName = semesterName; }

    public int getSemesterNo() { return semesterNo; }
    public void setSemesterNo(int semesterNo) { this.semesterNo = semesterNo; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public Double getCreditHours() { return creditHours; }
    public void setCreditHours(Double creditHours) { this.creditHours = creditHours; }
}
