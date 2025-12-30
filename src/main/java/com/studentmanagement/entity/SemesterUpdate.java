package com.studentmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "semester_update")
public class SemesterUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="student_id", unique=true, nullable=false)
    private String studentId;

    @Column(name="semester_name", nullable=false)
    private String semesterName;

    @Column(name="semester_no", nullable=false)
    private int semesterNo;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getSemesterName() { return semesterName; }
    public void setSemesterName(String semesterName) { this.semesterName = semesterName; }

    public int getSemesterNo() { return semesterNo; }
    public void setSemesterNo(int semesterNo) { this.semesterNo = semesterNo; }
}
