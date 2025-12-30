package com.studentmanagement.controller;

import com.studentmanagement.entity.StudentResult;
import com.studentmanagement.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ResultController {

    @Autowired
    private ResultService resultService;

    // =======================
    // 1️⃣ Save single course result
    // =======================
    @PostMapping("/save-results")
    public String saveResult(@RequestBody StudentResult result) {
        resultService.saveResult(result);
        return "Result saved for course " + result.getCourseCode();
    }

    // =======================
    // 2️⃣ Fetch existing results
    // =======================
    @GetMapping("/student-results")
    public List<StudentResult> getResults(
            @RequestParam String studentId,
            @RequestParam String semesterName
    ) {
        return resultService.getResults(studentId, semesterName);
    }
}
