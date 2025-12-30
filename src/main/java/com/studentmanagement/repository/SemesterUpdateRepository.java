package com.studentmanagement.repository;

import com.studentmanagement.entity.SemesterUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SemesterUpdateRepository extends JpaRepository<SemesterUpdate, Long> {
    Optional<SemesterUpdate> findByStudentId(String studentId);
}
