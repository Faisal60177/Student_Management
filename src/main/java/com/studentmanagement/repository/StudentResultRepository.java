package com.studentmanagement.repository;

import com.studentmanagement.entity.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {

    /**
     * Fetch all results of a student for a specific semester.
     *
     * @param studentId the student's ID
     * @param semesterName the name of the semester
     * @return list of StudentResult entities
     */
    List<StudentResult> findByStudentIdAndSemesterName(String studentId, String semesterName);
}
