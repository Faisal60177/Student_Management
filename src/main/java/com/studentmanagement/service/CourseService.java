package com.studentmanagement.service;

import com.studentmanagement.entity.Course;
import com.studentmanagement.entity.StudentCourse;
import com.studentmanagement.entity.Student;
import com.studentmanagement.entity.SemesterUpdate;
import com.studentmanagement.repository.CourseRepository;
import com.studentmanagement.repository.StudentCourseRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.repository.SemesterUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SemesterUpdateRepository semesterUpdateRepository;

    //==============================
    // FETCH AVAILABLE COURSES (Updated: Only dept + semesterNo)
    //==============================
    public List<Course> getAvailableCourses(
            String studentId,
            String department,
            String semesterName, // still passed but not used for filtering
            int semesterNo
    ) {
        // Fetch all courses for that dept & semesterNo
        List<Course> courses = courseRepository.findByDepartmentAndSemesterNo(department, semesterNo);

        // Fetch only the courses already registered by this student
        List<String> registeredCodes = studentCourseRepository.findCourseCodesByStudentId(studentId);

        // Return courses not yet registered
        return courses.stream()
                .filter(c -> !registeredCodes.contains(c.getCourseCode()))
                .collect(Collectors.toList());
    }

    // ==============================
    // REGISTER COURSES (ONLY REQUIRED UPDATE)
    // ==============================
    @Transactional
    public void registerCourses(
            String studentId,
            String department,
            String semesterName,
            int semesterNo,
            List<Map<String, String>> courseData
    ) {
        // 🔹 Fetch semester info from semester_update table
        Optional<SemesterUpdate> suOpt = semesterUpdateRepository.findByStudentId(studentId);
        if (suOpt.isEmpty()) {
            throw new RuntimeException("Semester info not found for student: " + studentId);
        }

        SemesterUpdate su = suOpt.get();
        String dbSemesterName = su.getSemesterName();
        int dbSemesterNo = su.getSemesterNo();

        List<String> courseCodes = courseData.stream()
                .map(c -> c.get("courseCode"))
                .toList();

        List<Course> courses = courseRepository.findByCourseCodeIn(courseCodes);

        List<StudentCourse> registrations = new ArrayList<>();

        for (Course c : courses) {
            // Skip already registered courses
            if (studentCourseRepository.existsByStudentIdAndCourseCode(studentId, c.getCourseCode())) {
                continue;
            }

            Map<String, String> data = courseData.stream()
                    .filter(cd -> cd.get("courseCode").equals(c.getCourseCode()))
                    .findFirst().orElse(null);

            if (data == null) continue;

            StudentCourse sc = new StudentCourse();
            sc.setStudentId(studentId);
            sc.setDepartment(department);

            // ✅ Semester values ALWAYS from semester_update table
            sc.setSemesterName(dbSemesterName);
            sc.setSemesterNo(dbSemesterNo);

            sc.setCourseName(c.getCourseName());
            sc.setCourseCode(c.getCourseCode());
            sc.setCreditHours(c.getCreditHours());
            sc.setSection(data.get("section"));
            sc.setSubSection(data.get("subSection"));

            registrations.add(sc);
        }

        studentCourseRepository.saveAll(registrations);
    }

    // ================= Admin Update Semester (No Change)
    @Transactional
    public String adminUpdateSemester(String semesterName, int semesterNo) {
        List<Student> students = studentRepository.findAll();

        for (Student student : students) {
            StudentCourse sc = new StudentCourse();
            sc.setStudentId(student.getStudentId());
            sc.setDepartment(student.getDepartment() != null ? student.getDepartment() : "UNKNOWN");
            sc.setSemesterName(semesterName);
            sc.setSemesterNo(semesterNo);

            sc.setCourseName("");
            sc.setCourseCode("");
            sc.setCreditHours(0.0);
            sc.setSection("");
            sc.setSubSection("");

            studentCourseRepository.save(sc);
        }

        return "Semester updated and placeholder rows created for all students";
    }

    // ==============================
    // FETCH REGISTERED COURSES
    // ==============================
    public List<StudentCourse> getRegisteredCourses(
            String studentId,
            String semesterName

    ) {
        return studentCourseRepository.findAll().stream()
                .filter(sc -> sc.getStudentId().equals(studentId)
                        && sc.getSemesterName().equalsIgnoreCase(semesterName)
                       )
                .collect(Collectors.toList());
    }

    // ==============================
    // GET SEMESTER INFO FROM semester_update
    // ==============================
    public Map<String, Object> getSemesterInfoByStudent(String studentId) {
        Optional<SemesterUpdate> suOpt = semesterUpdateRepository.findByStudentId(studentId);
        if (suOpt.isPresent()) {
            SemesterUpdate su = suOpt.get();
            Map<String, Object> map = new HashMap<>();
            map.put("semesterName", su.getSemesterName());
            map.put("semesterNo", su.getSemesterNo());
            return map;
        }
        return Map.of("semesterName", "N/A", "semesterNo", 0);
    }
}
