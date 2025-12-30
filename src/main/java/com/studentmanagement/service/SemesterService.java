package com.studentmanagement.service;

import com.studentmanagement.entity.SemesterUpdate;
import com.studentmanagement.repository.SemesterUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SemesterService {

    @Autowired
    private SemesterUpdateRepository semesterRepo;

    @Transactional
    public String saveOrUpdateSemester(String studentId, String semesterName, int semesterNo) {
        SemesterUpdate existing = semesterRepo.findByStudentId(studentId).orElse(null);

        if (existing != null) {
            existing.setSemesterName(semesterName);
            existing.setSemesterNo(semesterNo);
            semesterRepo.save(existing);
            return "Semester updated for student: " + studentId;
        } else {
            SemesterUpdate su = new SemesterUpdate();
            su.setStudentId(studentId);
            su.setSemesterName(semesterName);
            su.setSemesterNo(semesterNo);
            semesterRepo.save(su);
            return "Semester saved for student: " + studentId;
        }
    }

    public Optional<SemesterUpdate> getSemesterByStudentId(String studentId){
        return semesterRepo.findByStudentId(studentId);
    }
}
