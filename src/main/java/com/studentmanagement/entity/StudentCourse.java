package com.studentmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_course")
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private String studentId;

    private String department;

    @Column(name = "semester_name")
    private String semesterName;

    @Column(name = "semester_no")
    private int semesterNo;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "credit_hours")
    private Double creditHours;

    @Column(name = "section")
    private String section;

    @Column(name = "sub_section")
    private String subSection;

    // Getters & Setters
    public Long getId() { return id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

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

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public String getSubSection() { return subSection; }
    public void setSubSection(String subSection) { this.subSection = subSection; }
}
