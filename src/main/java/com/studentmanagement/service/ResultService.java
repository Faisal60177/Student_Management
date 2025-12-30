package com.studentmanagement.service;

import com.studentmanagement.entity.StudentResult;
import com.studentmanagement.repository.StudentResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private StudentResultRepository studentResultRepository; // Correct repository name

    // ===============================
    // Save single course result
    // ===============================
    @Transactional
    public void saveResult(StudentResult result) {
        // Optional: ensure grade_point is calculated before save
        if (result.getCreditHours() != null && result.getPoint() != null) {
            result.setGradePoint(result.getCreditHours() * result.getPoint());
        }

        studentResultRepository.save(result);
    }

    // ===============================
    // Fetch all results of a student for a semester
    // ===============================
    public List<StudentResult> getResults(String studentId, String semesterName) {
        return studentResultRepository.findByStudentIdAndSemesterName(studentId, semesterName);
    }
}
