package com.ousl.examinations.controller;

import com.ousl.examinations.dto.MarksCriteriaDTO;
import com.ousl.examinations.model.Component;
import com.ousl.examinations.model.Program;
import com.ousl.examinations.service.MarksCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks-criteria")
@CrossOrigin(origins = "*") // Allowing all origins for development
public class MarksCriteriaController {

    @Autowired
    private MarksCriteriaService marksCriteriaService;

    @GetMapping("/components")
    public ResponseEntity<List<Component>> getAllComponents() {
        return ResponseEntity.ok(marksCriteriaService.getAllComponents());
    }

    @GetMapping("/program/{programId}")
    public ResponseEntity<List<MarksCriteriaDTO>> getMarksCriteriaByProgram(@PathVariable Long programId) {
        return ResponseEntity.ok(marksCriteriaService.getMarksCriteriaByProgram(programId));
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveMarksCriteria(@RequestBody List<MarksCriteriaDTO> marksCriteriaDTOList) {
        marksCriteriaService.saveMarksCriteria(marksCriteriaDTOList);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/components/add")
    public ResponseEntity<Component> addNewComponent(@RequestParam String componentName) {
        Component newComponent = marksCriteriaService.addNewComponent(componentName);
        return ResponseEntity.ok(newComponent);
    }
}
