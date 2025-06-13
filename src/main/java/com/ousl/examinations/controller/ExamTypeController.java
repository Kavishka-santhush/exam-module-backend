package com.ousl.examinations.controller;

import com.ousl.examinations.payload.request.ExamTypeRequest;
import com.ousl.examinations.payload.response.ExamTypeResponse;
import com.ousl.examinations.service.ExamTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examtypes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamTypeController {

    @Autowired
    private ExamTypeService examTypeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExamTypeResponse> createExamType(@Valid @RequestBody ExamTypeRequest examTypeRequest) {
        ExamTypeResponse newExamType = examTypeService.createExamType(examTypeRequest);
        return new ResponseEntity<>(newExamType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExamTypeResponse> updateExamType(
            @PathVariable Long id, 
            @Valid @RequestBody ExamTypeRequest examTypeRequest) {
        ExamTypeResponse updatedExamType = examTypeService.updateExamType(id, examTypeRequest);
        return ResponseEntity.ok(updatedExamType);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExamTypeResponse> getExamTypeById(@PathVariable Long id) {
        ExamTypeResponse examType = examTypeService.getExamTypeById(id);
        return ResponseEntity.ok(examType);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ExamTypeResponse>> getAllExamTypes() {
        List<ExamTypeResponse> examTypes = examTypeService.getAllExamTypes();
        return ResponseEntity.ok(examTypes);
    }

    @GetMapping("/program/{programId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ExamTypeResponse>> getExamTypesByProgramId(@PathVariable Long programId) {
        List<ExamTypeResponse> examTypes = examTypeService.getExamTypesByProgramId(programId);
        return ResponseEntity.ok(examTypes);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteExamType(@PathVariable Long id) {
        examTypeService.deleteExamType(id);
        return ResponseEntity.noContent().build();
    }
}
