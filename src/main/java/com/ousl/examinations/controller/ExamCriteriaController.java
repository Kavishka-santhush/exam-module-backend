package com.ousl.examinations.controller;

import com.ousl.examinations.model.Program;
import com.ousl.examinations.model.ScExamProgMarksCriteria;
import com.ousl.examinations.payload.GradeCriteriaDTO;
import com.ousl.examinations.payload.SaveCriteriaBindingDTO;
import com.ousl.examinations.service.ExamCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam-criteria")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamCriteriaController {

    @Autowired
    private ExamCriteriaService examCriteriaService;

    @GetMapping("/programs")
    public List<Program> getAllPrograms() {
        return examCriteriaService.getAllPrograms();
    }

    @GetMapping("/grades")
    public List<GradeCriteriaDTO> getGradesAndCriteria() {
        return examCriteriaService.getGradesAndCriteria();
    }

    @GetMapping("/saved-criteria/{programId}")
    public List<ScExamProgMarksCriteria> getSavedCriteria(@PathVariable Long programId) {
        return examCriteriaService.getSavedCriteria(programId);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCriteriaBindings(@RequestBody SaveCriteriaBindingDTO dto) {
        examCriteriaService.saveCriteriaBindings(dto);
        return ResponseEntity.ok().build();
    }
}
