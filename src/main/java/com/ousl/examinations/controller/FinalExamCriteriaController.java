package com.ousl.examinations.controller;

import com.ousl.examinations.model.FinalExamCriteria;
import com.ousl.examinations.service.FinalExamCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/final-exam-criteria")
@CrossOrigin(origins = "http://localhost:4200")
public class FinalExamCriteriaController {

    @Autowired
    private FinalExamCriteriaService finalExamCriteriaService;

    @GetMapping
    public List<FinalExamCriteria> getAllFinalExamCriteria() {
        return finalExamCriteriaService.getAllFinalExamCriteria();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinalExamCriteria> getFinalExamCriteriaById(@PathVariable Long id) {
        FinalExamCriteria finalExamCriteria = finalExamCriteriaService.getFinalExamCriteriaById(id);
        if (finalExamCriteria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(finalExamCriteria);
    }

    @PostMapping
    public FinalExamCriteria createFinalExamCriteria(@RequestBody FinalExamCriteria finalExamCriteria) {
        return finalExamCriteriaService.createFinalExamCriteria(finalExamCriteria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinalExamCriteria> updateFinalExamCriteria(@PathVariable Long id, @RequestBody FinalExamCriteria finalExamCriteriaDetails) {
        FinalExamCriteria updatedFinalExamCriteria = finalExamCriteriaService.updateFinalExamCriteria(id, finalExamCriteriaDetails);
        return ResponseEntity.ok(updatedFinalExamCriteria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinalExamCriteria(@PathVariable Long id) {
        finalExamCriteriaService.deleteFinalExamCriteria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/grades")
    public List<String> getDistinctGrades() {
        return finalExamCriteriaService.getDistinctGrades();
    }
}
