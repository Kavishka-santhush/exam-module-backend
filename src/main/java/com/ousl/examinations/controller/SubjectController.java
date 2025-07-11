package com.ousl.examinations.controller;


import com.ousl.examinations.model.Subject;
import com.ousl.examinations.service.Impl.SubjectServiceImpl;

import com.ousl.examinations.service.Impl.SubjectServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequestMapping("/api/subject")
@RestController
@CrossOrigin(maxAge = 3600,origins = "*")

public class SubjectController {

    @Autowired
    private SubjectServiceImpl subjectService;

    @PostMapping("/createSubject")
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);

    }

    @GetMapping("getSubjectById/{id}")
    public Subject getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    @PutMapping("updateSubject/{id}")
    public Subject updateSubject(@PathVariable Long id, @RequestBody Subject subject) {
        return subjectService.updateSubject(id, subject);
    }

    @DeleteMapping("/deleteSubject/{id}")
    public boolean deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
//        return "Subject deleted successfully";
        return subjectService.deleteSubject(id);
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }
    @GetMapping("/byProgram/{programId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Subject>> getSubjectsByProgramId(@PathVariable Long programId) {
        List<Subject> subjects = subjectService.getSubjectsByProgramId(programId);
        return ResponseEntity.ok(subjects);
    }
}

