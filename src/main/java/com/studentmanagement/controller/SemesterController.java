package com.studentmanagement.controller;

import com.studentmanagement.entity.SemesterUpdate;
import com.studentmanagement.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    @PostMapping("/save-semester")
    public String saveSemester(@RequestBody Map<String, String> payload) {
        String studentId = payload.get("studentId");
        String semesterName = payload.get("semesterName");
        int semesterNo;

        try {
            semesterNo = Integer.parseInt(payload.get("semesterNo"));
        } catch (NumberFormatException e) {
            return "Invalid semester number";
        }

        return semesterService.saveOrUpdateSemester(studentId, semesterName, semesterNo);
    }

    // --- NEW: Fetch semester info by studentId ---
    @GetMapping("/semester/{studentId}")
    public SemesterUpdate getSemesterByStudentId(@PathVariable String studentId) {
        return semesterService.getSemesterByStudentId(studentId).orElse(null);
    }
}
