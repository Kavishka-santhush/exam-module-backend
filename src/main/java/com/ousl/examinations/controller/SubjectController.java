package com.ousl.examinations.controller;


import com.ousl.examinations.model.Program;
import com.ousl.examinations.model.Subject;
import com.ousl.examinations.service.Impl.SubjectServiceImpl;
import com.ousl.examinations.service.SubjectService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequestMapping("/api/subjects")
@CrossOrigin(maxAge = 3600,origins = "*")
public class SubjectController {

    @Autowired
    private SubjectServiceImpl subjectService;
    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }


    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }


    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }


    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable Long id, @RequestBody Subject subject) {
        return subjectService.updateSubject(id, subject);
    }


    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return "Subject deleted successfully";
    }
}

